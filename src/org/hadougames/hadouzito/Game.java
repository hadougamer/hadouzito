package org.hadougames.hadouzito;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {
	/* Attributes */
	// Version
	private static final long serialVersionUID = 1L;
	// Run control
	private boolean isRunning;
	// Game Thread
	private Thread thread;
	// Game Level
	private Level level;
	// Current level number
	private int curLevel = 1;
	// Game Hero
	public static Hero hero;
	// Game Draguinho
	public static Draguinho draguinho;
	// Game Screen
	private Screen screen;
	
	/* Constants */
	private final double FPS = 30.0;
	public static final int WIDTH  = 320;
	public static final int HEIGHT = 200;
	private final int SCALE  = 5;
	private final boolean DEBUG  = false;
	
	private BufferedImage image;
	
	/* Constructor */
	public Game() {		
		// Running control
		isRunning = true;

		// Game Dimensions
		setPreferredSize(new Dimension(
			(WIDTH * SCALE), (HEIGHT  * SCALE)
		));
		
		// Loads the Screen
		screen = new Screen("Hadouzito Adventures - Aplha");
		screen.load(this);
		// Loads the Hero (pos_x: 0, pos_y: 0);
		hero = new Hero(16, ( HEIGHT - 16 - 7) );
		draguinho = new Draguinho(100, ( HEIGHT - 16 - 7) );
		image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_BGR);

		// Loads the level
		level = new Level();
		level.load(curLevel);
		
		// Starts Key Listening
		this.addKeyListener(this);
	}
	
	/* Main method */
	public static void main( String[] args ) {
		System.out.println("Lets get started the game");
		
		// Initialize the game
		Game game = new Game();
		game.start();
	}
	
	/* Local methods */
	// Loop game logic
	public void tick() {
		showDebug(hero.getAction());
	}
	
	// Loop game render
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if ( bs == null ) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		
		// Draw the background retangle
		g.setColor(new Color(100, 100, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//
		Graphics2D g2 = (Graphics2D) g; // Cast to Graphics2D
		level.render(g2);
		hero.render(g2);
		draguinho.render(g2);
		// ----------
		
		g.dispose(); // Garbage collector
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, (WIDTH*SCALE), (HEIGHT*SCALE),null);
		bs.show();
	}
	
	// Starts the game
	public void start() {
		// Starts the game thread
		thread = new Thread(this);
		thread.start();
	}
	
	// Stops the game
	public void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/* KeyListener methods */
	public void keyPressed(KeyEvent e) {
		String heroAction = "stop";
		String dragAction = "stop";
		
		switch( e.getKeyCode() ) {
			case KeyEvent.VK_RIGHT:
				heroAction = "walk-right";
				dragAction = "walk-left";
			break;
			case KeyEvent.VK_LEFT:
				heroAction = "walk-left";
				dragAction = "walk-right";
			break;
			case KeyEvent.VK_SPACE:
				heroAction = "jump";				
			break;
		}
		hero.doAction(heroAction);
		draguinho.doAction(dragAction);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// On Key Up
		hero.doAction("stop");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// On Key Typed
	}
	
	/* Runnable method */	
	public void run() {
		//FPS
		long lastTime 		 = System.nanoTime();
		double amountOfTicks = FPS;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while( isRunning ) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if ( delta >= 1 ) {
				tick();
				render();
				
				// Frame test
				frames++;
				delta--;
			}
			
			if ((System.currentTimeMillis()-timer) >= 1000 ) {
				showDebug("FPS: " + frames);
				frames = 0;
				timer+=1000;
			}
		}
		stop();
	}
	
	/* Debug */
	private void showDebug( String message ) {
		if ( DEBUG ) {
			System.out.println( message );	
		}
	}
	
}
package org.hadougames.hadouzito;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends Canvas implements Runnable, KeyListener {
	/* Attributes */
	// Version
	private static final long serialVersionUID = 1L;
	// Run control
	private boolean isRunning;
	// Game Thread
	private Thread thread;
	// Game Hero
	private Hero hero;
	// Game Screen
	private Screen screen;
	
	/* Constants */
	private final double FPS = 30.0;
	private final int WIDTH  = 640;
	private final int HEIGHT = 480;
	private final int SCALE  = 1;
	private final boolean DEBUG  = true;
	
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
		// Loads the Hero
		hero = new Hero();
		
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
		//showDebug("render ...");
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
		String curAction = "stop";
		
		switch( e.getKeyCode() ) {
			case KeyEvent.VK_RIGHT:
				curAction = "walk-right";
			break;
			case KeyEvent.VK_LEFT:
				curAction = "walk-left";
			break;
			case KeyEvent.VK_SPACE:
				curAction = "jump";				
			break;
		}
		hero.doAction(curAction);
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
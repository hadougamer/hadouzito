package org.hadougames.hadouzito;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Draguinho extends Character {
	// Gravity switch
	private boolean gravity = false;
	
	private String spritePath = "/draguinho.png";
	private String action;
	private int points;
	
	// Controls the Hero frame update
	private int frameControl = 0;
	private int maxFrameControl = 4;

	private boolean dirRight = false;
	
	public Draguinho(int pos_x, int pos_y) {
		// Sets the character Sprite
		setSprite( spritePath );
		
		setName("Draguinho");
		setWeight(4.5);
		
		action = "stop";
		points = 0;
		
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		 
		// Load frames by action
		loadFrames( action );
	}
	
	public void doAction( String act ) {
		// Filter action
		switch( act ) {
			case "walk-right":
				dirRight = true;
				pos_x+=2;
				action = act;
			break;			
			case "walk-left":
				dirRight = false;
				pos_x-=2;
				action = act;
			break;
			case "jump":
				action = act;
			break;
			default:
				action = "stop";
			break;	
		}
	}
	
	public String getAction() {
		return action;
	}
	
	public int getPoints() {
		return points;
	}
	
	public double getPosX() {
		return pos_x;
	}

	public double getPosY() {
		if( gravity ) {
			return pos_y + getGravityFactor();	
		}
		
		return pos_y;
	}

	public void setPosX( int pos_x ) {
		this.pos_x = pos_x;
	}

	public void setPosY( int pos_y) {
		this.pos_y = pos_y;
	}	
	
	public BufferedImage getFrame() {
		// Update the frame list by action
		loadFrames( getAction() );
		
		int maxFrame = ( frames.size() - 1 );
		
		if( currentFrameNumber > maxFrame ) {
			currentFrameNumber = 0;
		}
		
		BufferedImage frame = frames.get(currentFrameNumber);
		
		frameControl++;
		// Updates sprite every maxFrameControl
		if ( frameControl == maxFrameControl ) {
			frameControl = 0;
			currentFrameNumber++;	
		}

		return frame;
	}
	
	private void loadFrames( String act ) {
		cleanFrames();
		// Filter action
		switch( act ) {
			case "walk-right":
				frames.add(sprite.getSprite(0, 0, 16, 16));
				frames.add(sprite.getSprite(16, 0, 16, 16));
				frames.add(sprite.getSprite(32, 0, 16, 16));
				frames.add(sprite.getSprite(48, 0, 16, 16));
			break;
			case "walk-left":
				frames.add(sprite.getSprite(0, 16, 16, 16));
				frames.add(sprite.getSprite(16, 16, 16, 16));
				frames.add(sprite.getSprite(32, 16, 16, 16));
				frames.add(sprite.getSprite(48, 16, 16, 16));
			break;
			default:
				if( dirRight ) {
					frames.add(sprite.getSprite(16, 0, 16, 16));	
				} else {
					frames.add(sprite.getSprite(16, 16, 16, 16));
				}
			break;	
		}
	}
	
	public void render( Graphics g ) {
		g.drawImage(
			getFrame(), 
			(int) (getPosX() - Camera.x), 
			(int) (getPosY() - Camera.y), 
			null
		);
	}
}

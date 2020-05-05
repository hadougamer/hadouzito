package org.hadougames.hadouzito;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hero {
	private String name, action;
	private int points;
	
	// Hero Sprite
	private Sprite sprite = new Sprite("/hadouzito.png");
	
	// Hero frames
	private int currentFrameNumber = 0;
	private ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	
	private int pos_x, pos_y;
	
	public Hero(int pos_x, int pos_y) {
		name  = "Hadouzito";
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
				pos_x+=2;
				action = act;
			break;			
			case "walk-left":
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
	
	public String getName() {
		return name;
	}
	
	public String getAction() {
		return action;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getPosX() {
		return pos_x;
	}

	public int getPosY() {
		return pos_y;
	}	
	
	public BufferedImage getFrame() {
		// Update the frame list by action
		loadFrames( getAction() );
		
		int maxFrame = ( frames.size() - 1 );
		
		if( currentFrameNumber > maxFrame ) {
			currentFrameNumber = 0;
		}
		
		BufferedImage frame = frames.get(currentFrameNumber);
		
		currentFrameNumber++;
		
		return frame;
	}
	
	private void loadFrames( String act ) {
		cleanFrames();
		if ( act == "stop" ) {
			frames.add(sprite.getSprite(16, 0, 16, 16));	
		} else {
			frames.add(sprite.getSprite(0, 0, 16, 16));
			frames.add(sprite.getSprite(16, 0, 16, 16));
			frames.add(sprite.getSprite(32, 0, 16, 16));
			frames.add(sprite.getSprite(48, 0, 16, 16));
		}
	}
	
	private void cleanFrames() {
		frames.clear();
	}
}

package org.hadougames.hadouzito;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hero {
	private String name, action;
	private int points;
	
	// Hero Sprite
	private Sprite sprite = new Sprite("/hadouzito.png");
	
	// Hero frames
	private ArrayList<BufferedImage> frames;
	
	public Hero() {
		name  = "Hadouzito";
		action = "stop";
		points = 0;
		
		// Load frames by action
		loadFrames( action );
	}
	
	public void doAction( String act ) {
		// Filter action
		switch( act ) {
			case "walk-right":
			case "walk-left":
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
	
	private void loadFrames( String act ) {
		cleanFrames();
	}
	
	private void cleanFrames() {
		frames = new ArrayList<BufferedImage>();
	}
}

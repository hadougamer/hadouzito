package org.hadougames.hadouzito;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

abstract class Character extends Physic {
	// Character Frames
	protected int currentFrameNumber = 0;
	protected ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>();
	
	// State
	protected int pos_x, pos_y;
	protected double weight = 4.0;
	
	protected String spritePath, name;
	protected Sprite sprite;
	
	protected void setSprite( String path ) {
		spritePath = path;
		sprite = new Sprite( spritePath );
	}
	
	protected void setName( String name ) {
		this.name = name;
	}
	
	protected void setWeight( double weight ) {
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	
	public double getWeight() {
		return weight;
	}
}

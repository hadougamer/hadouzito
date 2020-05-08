package org.hadougames.hadouzito;

import java.awt.Graphics;

public class Level {
	private Map map;
	private int levelNumber = 1;
	
	// Load level assets
	public void load( int levelNumber ) {
		setLevel( levelNumber );
		setMap( levelNumber );
	}
	
	public int getLevel() {
		return levelNumber;
	}
	
	private void setLevel( int levelNumber ) {
		this.levelNumber = levelNumber;
	}
	
	private void setMap( int levelNumber ) {
		map = new Map( levelNumber) ;
	}
	
	public void render( Graphics g ) {
		map.render(g);
	}
}

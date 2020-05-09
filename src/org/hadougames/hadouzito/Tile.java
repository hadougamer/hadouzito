package org.hadougames.hadouzito;

import java.awt.image.BufferedImage;

public class Tile {
	
	public static int WIDTH = 16;
	public static int HEIGHT = 16;
	private int x;
	private int y;
	private BufferedImage tileImage;
	
	private Sprite sprite;
	private String spritePath = "/hadouzito.png";
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		sprite = new Sprite( spritePath );
	}
	
	public void setFloor() {
		tileImage = sprite.getSprite(0, 32, WIDTH, HEIGHT);
	}
	
	public void setWall() {
		tileImage = sprite.getSprite(16, 32, WIDTH, HEIGHT);
	}
	
	public BufferedImage getImage() {
		return tileImage;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
}

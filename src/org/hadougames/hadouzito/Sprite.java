package org.hadougames.hadouzito;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage spritesheet;

	// Loads a sprite from a path
	public Sprite( String path ) {
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Cuts the image frame from sprite
	public BufferedImage getSprite( int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
}

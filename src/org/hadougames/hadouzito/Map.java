package org.hadougames.hadouzito;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
- Loads the Map Image
- Analyze each pixels
- Create a grit with its information 

*/
public class Map {
	
	private String path;
	private Sprite sprite;
	private BufferedImage mapImage;
	
	protected ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	/*
 	 0xFFFF0000 - Vermelho
 	 0xFF0083ff - Azul
 	 0xFFFFec00 - Amarelo
 	 0xFF8BFF00 - Verde
 	 0xFF9600FF - Roxo
	 */
	
	private final int PX_FLOOR = 0;
	private final int PX_WALL  = 1;
	
	public Map( int levelNumber ) {
		String[] levelMaps = new String[1];
		levelMaps[0] = "/HadouMap.png";
		
		path = levelMaps[(levelNumber -1 )];
		
		sprite = new Sprite(path);
		mapImage = sprite.getImage();
		
		analyze();
	}
	
	// Analyze the pixels
	private void analyze() {
		int[] imgPixels = new int[mapImage.getWidth() * mapImage.getHeight()];
		
		mapImage.getRGB(
				0, 
				0, 
				mapImage.getWidth(), 
				mapImage.getHeight(), 
				imgPixels, 
				0, 
				mapImage.getWidth()
		);

		int pixelPos=0;
		int posX = 0;
		int posY = 0;
		
		for ( int w=0; w<mapImage.getWidth(); w++) {
			for ( int h=0; h<mapImage.getHeight(); h++) {
				System.out.println("X: " + posX + " - Y: " + posY);
				Tile newTile = new Tile(posX, posY);
				
				if( imgPixels[pixelPos] == 0xFFFFFFFF ) {
					newTile.setWall();
				} else {
					newTile.setFloor();
				}
				
				tiles.add( newTile );
				pixelPos++;
				
				posX += Tile.WIDTH;
				if ( posX >= (mapImage.getWidth()*16) )
					posX=0;				
			}
			posY += Tile.HEIGHT;
			if ( posY >= (mapImage.getHeight()*16) )
				posY=0;
		}
	}
	
	public void render( Graphics g ) {
		for( int i=0; i<tiles.size(); i++ ) {
			g.drawImage(
					tiles.get(i).getImage(), 
					tiles.get(i).getX(), 
					tiles.get(i).getY(), 
					null
				);

		}
	}
}

package util;

import java.awt.Color;
import java.awt.image.BufferedImage;

import blocks.*;
import entities.Player;
import states.LevelState;

public class GameLevelLoader {

	private int level;
	private int width;
	private int height;

	private int numEntities;

	private Color[][] colorMap;

	private BufferedImage image;

	public GameLevelLoader(int level) {
		this.level = level;
		if (this.level == 1) {
			image = Assets.LEVEL_1;
		} else if (this.level == 2) {
			image = Assets.LEVEL_2;
		} else if (this.level == 3) {

		}
		width = image.getWidth();
		height = image.getHeight();
		createColorMap();
	}

	public void loadLevel(LevelState ls) {
		int entityCounter = 0;
//		for (int x = 0; x < width; x++) {
//			for (int y = 0; y < height; y++) {
//				if (colorMap[x][y].equals(Color.BLACK)) {
//					ls.addEntity(new Player(ls, x * Block.SIZE, y * Block.SIZE, entityCounter));
//					entityCounter++;
//				}
//			}
//		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (colorMap[x][y].equals(Color.WHITE))
					continue;
				if (colorMap[x][y].equals(Color.BLACK)) { // Aggiungo i blocchi neri come entità di tipo PLAYER
					ls.addEntity(new Player(ls, x * Block.SIZE, y * Block.SIZE, entityCounter));
					entityCounter++;
				}
				if (colorMap[x][y].equals(Dirt.COLOR)) { // Aggiungo i blocchi verdi come blocchi di tipo DIRT
					if(y > 0) {
						if(colorMap[x][y-1].equals(Dirt.COLOR)) { // Se il blocco sopra è anch'esso DIRT, allora uso la texture senza erba
							ls.addBlock(new Dirt(ls, x * Dirt.WIDTH, y * Dirt.HEIGHT, Assets.DIRT));
						} else {
							ls.addBlock(new Dirt(ls, x * Dirt.WIDTH, y * Dirt.HEIGHT, Assets.GRASS));
						}
					} else {
						ls.addBlock(new Dirt(ls, x * Dirt.WIDTH, y * Dirt.HEIGHT, Assets.GRASS));
					}
				}
				if (colorMap[x][y].equals(Ice.COLOR)) { // Aggiungo i blocchi blu come blocchi di tipo ICE
					ls.addBlock(new Ice(ls, x * Ice.WIDTH, y * Ice.HEIGHT, Assets.ICE));
				}
				if (colorMap[x][y].equals(Sand.COLOR)) { // Aggiungo i blocchi blu come blocchi di tipo ICE
					ls.addBlock(new Ice(ls, x * Sand.WIDTH, y * Sand.HEIGHT, Assets.SAND));
				}
			}
		}
		numEntities = entityCounter;
	}

	private void createColorMap() {
		colorMap = new Color[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				colorMap[x][y] = new Color(image.getRGB(x, y));
			}
		}
	}
	
	public Color[][] getColorMap() {
		if(colorMap != null) return colorMap;
		else return null;
	}
	
	public int getEntitiesSize() {
		return numEntities;
	}

}

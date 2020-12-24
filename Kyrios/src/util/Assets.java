package util;

import java.awt.image.BufferedImage;

import graphics.Sprite;

public class Assets {

	public static Sprite PLAYER;
	public static Sprite TERRAIN;
	public static BufferedImage GRASS;
	public static BufferedImage DIRT;
	public static BufferedImage ICE;
	public static BufferedImage SAND;
	public static BufferedImage LEVEL_1;
	public static BufferedImage LEVEL_2;
	public static BufferedImage BUTTON_PLAY_ACTIVE, BUTTON_PLAY_INACTIVE;
	public static BufferedImage BUTTON_LIVELLO_ACTIVE, BUTTON_LIVELLO_INACTIVE;
	public static BufferedImage BUTTON_BACK_ACTIVE, BUTTON_BACK_INACTIVE;
	public static BufferedImage BUTTON_LEVEL1_ACTIVE, BUTTON_LEVEL1_INACTIVE;
	public static BufferedImage BUTTON_LEVEL2_ACTIVE, BUTTON_LEVEL2_INACTIVE;
	public static BufferedImage GAME_OVER;
	
	public static void loadSprites() {
		PLAYER = new Sprite("KyriosSpriteSheet.png", 16, 16);
		TERRAIN = new Sprite("TerrainSpriteSheet.png", 16, 16);
		GRASS = TERRAIN.getSprite(0, 0);
		DIRT = TERRAIN.getSprite(1, 0);
		ICE = TERRAIN.getSprite(2, 0);
		SAND = TERRAIN.getSprite(3, 0);
		LEVEL_1 = new Sprite("levels/Level1.png").getSpriteSheet();
		LEVEL_2 = new Sprite("levels/LevelTest.png").getSpriteSheet();
		BUTTON_PLAY_INACTIVE = new Sprite("buttons/bottone_prova_inactive.png").getSpriteSheet();
		BUTTON_PLAY_ACTIVE = new Sprite("buttons/bottone_prova_active.png").getSpriteSheet();
		BUTTON_LIVELLO_INACTIVE = new Sprite("buttons/bottone_livello_inactive.png").getSpriteSheet();
		BUTTON_LIVELLO_ACTIVE = new Sprite("buttons/bottone_livello_active.png").getSpriteSheet();
		BUTTON_BACK_INACTIVE = new Sprite("buttons/bottone_back_inactive.png").getSpriteSheet();
		BUTTON_BACK_ACTIVE = new Sprite("buttons/bottone_back_active.png").getSpriteSheet();
		BUTTON_LEVEL1_INACTIVE = new Sprite("buttons/bottone_livello1_inactive.png").getSpriteSheet();
		BUTTON_LEVEL1_ACTIVE = new Sprite("buttons/bottone_livello1_active.png").getSpriteSheet();
		BUTTON_LEVEL2_INACTIVE = new Sprite("buttons/bottone_livello2_inactive.png").getSpriteSheet();
		BUTTON_LEVEL2_ACTIVE = new Sprite("buttons/bottone_livello2_active.png").getSpriteSheet();
		GAME_OVER = new Sprite("game_over.png").getSpriteSheet();
		
		System.out.println("Complete loading : ALL sprites and images");
	}
}

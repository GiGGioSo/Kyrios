package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import util.Vector2d;

// RICORDA CHE LA Y E LA X SONO INVERTITE IN QUESTA CLASSE

public class Sprite {

	private BufferedImage SPRITESHEET = null;
	private BufferedImage[][] spriteArray;
	//private final int DEFAULT_TILE_SIZE = 16;
	private int w; // Width di una singola TILE
	private int h; // Height di una singola TILE
	private int wSprite; // Numero di TILE sull'asse x
	private int hSprite; // Numero di TILE sull'asse y

	// RICORDA CHE LA Y E LA X SONO INVERTITE IN QUESTA CLASSE
	
	public Sprite(String path, int w, int h) {
		this.w = w;
		this.h = h;

		System.out.println("Loading : " + path + "...");
		SPRITESHEET = loadImage(path);

		wSprite = SPRITESHEET.getWidth() / w;
		hSprite = SPRITESHEET.getHeight() / h;
		loadSpriteArray();
	}

	public Sprite(String path, int size) {
		this(path, size, size);
	}
	
	public Sprite(String path) {
		System.out.println("Loading : " + path + "...");
		SPRITESHEET = loadImage(path);
	}

	// RICORDA CHE LA Y E LA X SONO INVERTITE IN QUESTA CLASSE
	
	private BufferedImage loadImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
			System.out.println("Complete loading : " + path);
		} catch (Exception e) {
			System.out.println("ERROR : Couldn't load the image : " + path);
		}
		return img;
	}

	private void loadSpriteArray() {
		spriteArray = new BufferedImage[hSprite][wSprite];
		for (int y = 0; y < hSprite; y++) {
			for (int x = 0; x < wSprite; x++) {
				spriteArray[y][x] = getSprite(x, y);
			}
		}
	}

	// RICORDA CHE LA Y E LA X SONO INVERTITE IN QUESTA CLASSE
	
	public void setSize(int w, int h) {
		this.w = w;
		this.h = h;
		wSprite = SPRITESHEET.getWidth() / w;
		hSprite = SPRITESHEET.getHeight() / h;
	}

	public void setSize(int size) {
		setSize(size, size);
	}

	public BufferedImage getSprite(int x, int y) {
		if (SPRITESHEET != null) {
			return SPRITESHEET.getSubimage(x * w, y * h, w, h);
		} else
			return null;
	}
	
	// RICORDA CHE LA Y E LA X SONO INVERTITE IN QUESTA CLASSE

	public BufferedImage getSpriteSheet() {
		return SPRITESHEET;
	}

	public BufferedImage[] getSpriteArray(int i) {
		return spriteArray[i];
	}

	public BufferedImage[][] getFullSpriteArray() {
		return spriteArray;
	}

	public void drawTile(Graphics2D g, Vector2d pos, int x, int y, int width, int height) {
		if (spriteArray != null) {
			g.drawImage(spriteArray[y][x], (int) pos.getX(), (int) pos.getY(), width, height, null);
		}
	}
	
	// RICORDA CHE LA Y E LA X SONO INVERTITE IN QUESTA CLASSE

	public static void drawArray(Graphics2D g, ArrayList<BufferedImage> img, Vector2d pos, int width, int height,
			int xOff, int yOff) {
		float x = pos.getX();
		float y = pos.getY();

		for (int i = 0; i < img.size(); i++) {
			if (img.get(i) != null) {
				g.drawImage(img.get(i), (int) x, (int) y, width, height, null);
			}
			x += xOff;
			y += yOff;
		}
	}
}

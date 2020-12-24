package blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import states.LevelState;
import util.Vector2d;

public class Dirt extends Block {
	
	public static final Color COLOR = Color.GREEN;
	
	public static final float FRICTION = .7f;
	
	public static int WIDTH = SIZE;
	public static int HEIGHT = SIZE;

	public Dirt(LevelState ls, Vector2d pos, BufferedImage texture) {
		this(ls, (int) pos.getX(), (int) pos.getY(), texture);
	}
	
	public Dirt(LevelState ls, int x, int y, BufferedImage texture) {
		super(ls, x, y, WIDTH, HEIGHT, FRICTION, texture);
		jumpPercentageDeficit = 0;
	}

	@Override
	public void update() { }

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(texture, (int) (x - Vector2d.getWorldX()), (int) (y - Vector2d.getWorldY()), WIDTH, HEIGHT, null);
	}

}

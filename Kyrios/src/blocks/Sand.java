package blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import states.LevelState;
import util.Vector2d;

public class Sand extends Block {
	
	public static final Color COLOR = new Color(255, 150, 0);
	
	public static final float FRICTION = .8f;
	
	public static int WIDTH = SIZE;
	public static int HEIGHT = SIZE;

	public Sand(LevelState ls, Vector2d pos, BufferedImage texture) {
		this(ls, (int) pos.getX(), (int) pos.getY(), texture);
	}

	public Sand(LevelState ls, int x, int y, BufferedImage texture) {
		super(ls, x, y, WIDTH, HEIGHT, FRICTION, texture);
		jumpDeficit = .3f;
		speedDeficit = .55f;
	}

	@Override
	public void update() { }

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(texture, (int) (x - Vector2d.getWorldX()), (int) (y - Vector2d.getWorldY()), WIDTH, HEIGHT, null);
	}

}

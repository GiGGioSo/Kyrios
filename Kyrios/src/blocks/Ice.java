package blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import states.LevelState;
import util.Vector2d;

public class Ice extends Block {
	
	public static final Color COLOR = Color.BLUE;

	public static final float FRICTION = .08f;

	public static int WIDTH = SIZE;
	public static int HEIGHT = SIZE;

	public Ice(LevelState ls, Vector2d pos, BufferedImage texture) {
		this(ls, (int) pos.getX(), (int) pos.getY(), texture);
	}

	public Ice(LevelState ls, int x, int y, BufferedImage texture) {
		super(ls, x, y, WIDTH, HEIGHT, FRICTION, texture);
		jumpPercentageDeficit = 0;
	}

	@Override
	public void update() {
		updateOld();
		updateBounds();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(texture, (int) (x - Vector2d.getWorldX()), (int) (y - Vector2d.getWorldY()), WIDTH, HEIGHT, null);
	}

}

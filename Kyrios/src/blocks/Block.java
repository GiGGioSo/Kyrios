package blocks;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import graphics.Sprite;
import states.LevelState;
import util.RectangleP;
import util.Vector2d;

public abstract class Block extends RectangleP {

	public static int SIZE = 64;

	protected LevelState ls;

	protected float friction;
	
	protected float jumpDeficit;

	protected Sprite sprite;

	protected BufferedImage texture;

	public Block(LevelState ls, Vector2d pos, int width, int height, float friction, BufferedImage texture) {
		this(ls, (int) pos.getX(), (int) pos.getY(), width, height, friction, texture);
	}

	public Block(LevelState ls, int x, int y, int width, int height, float friction, BufferedImage texture) {
		super(x, y, width, height);
		this.texture = texture;
		this.friction = friction;
		this.ls = ls;
	}

	public abstract void update();

	public abstract void draw(Graphics2D g);

	public float getFriction() {
		return friction;
	}
	
	public float getJumpDeficit() {
		return jumpDeficit;
	}
}

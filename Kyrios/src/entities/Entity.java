package entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

import blocks.Block;
import graphics.Sprite;
import input.KeyHandler;
import input.MouseHandler;
import states.LevelState;
import util.RectangleP;
import util.Vector2d;

public abstract class Entity extends RectangleP {
	
	public static final int DEFAULT_SIZE = 64;

	public int ID;

	protected float friction;

	protected LevelState ls;

	protected Sprite sprite;

	protected Vector2d vel;
	
	public static final float DEFAULT_JUMP_POWER = 11;
	
	protected float jumpPower = 11;

	protected boolean grounded = false;
	protected boolean wasGrounded = false;
	
	protected Block wasOnTopOf;

	public Entity(LevelState ls, Vector2d pos, int width, int height, int ID) {
		this(ls, (int) pos.getX(), (int) pos.getY(), width, height, ID);
	}

	public Entity(LevelState ls, int x, int y, int width, int height, int ID) {
		super(x, y, width, height);
		this.ls = ls;
		vel = new Vector2d();
		this.ID = ID;
	}

	public abstract void update();

	public abstract void draw(Graphics2D g);

	public abstract void input(MouseHandler mouse, KeyHandler key);
	
	public void collideWithBlocks(ArrayList<Block> b) {
		RectangleP hitBox = new RectangleP(this);
		grounded = false; // partiamo dal presupposto che non sia grounded, poi vedremo se è il contrario
		
		// HORIZONTAL COLLISION
		hitBox.addX(vel.getX()); // faccio una previsione del futuro in base alla velocita corrente
		for(Block block : b) {
			if(hitBox.doesItCollideWith(block)) {
				hitBox.addX(-vel.getX()); // se collide allora torno indietro
				while(!block.doesItCollideWith(hitBox)) hitBox.addX(Math.signum(vel.getX()) * .1f); // e inizio a farlo muovere finchè non tocca il blocco
				hitBox.addX(-Math.signum(vel.getX()) * .1f); // una volta che ha toccato il blocco lo faccio tornare indietro di un pixel cosi non interseca ancora
				vel.setX(0);
			}
		}
		
		// VERTICAL COLLISION
		hitBox.addY(vel.getY()); // faccio una previsione del futuro in base alla velocita corrente
		for(Block block : b) {
			if(hitBox.doesItCollideWith(block)) {
				hitBox.addY(-vel.getY()); // se collide allora torno indietro
				while(!block.doesItCollideWith(hitBox)) hitBox.addY(Math.signum(vel.getY()) * .1f); // e inizio a farlo muovere finchè non tocca il blocco
				hitBox.addY(-Math.signum(vel.getY()) * .1f); // una volta che ha toccato il blocco lo faccio tornare indietro di un pixel cosi non interseca ancora
				if(this.y < block.getY()) {
					grounded = true;
					friction = block.getFriction();
					this.jumpPower = DEFAULT_JUMP_POWER - DEFAULT_JUMP_POWER * block.getJumpDeficit(); // non so perche non va
				}
				vel.setY(0);
			}
		}
		this.setX(hitBox.getX());
		this.setY(hitBox.getY());
	}

	public float getFriction() {
		return friction;
	}
	
	public void setFriction(float f) {
		friction = f;
	}

	public abstract void updateImageCoords();

	public void setGroundedTo(boolean grounded) {
		this.grounded = grounded;
	}
	
	public void setWasOnBlock(Block b) {
		this.wasOnTopOf = b;
	}

	public void setVelX(float i) {
		vel.setX(i);
	}

	public void setVelY(float i) {
		vel.setY(i);
	}

	public void setVel(Vector2d vec) {
		vel.setVector(vec);
	}

	public void setVel(float x, float y) {
		vel.setVector(x, y);
	}
	
	public Block getWasOnBlock() {
		return wasOnTopOf;
	}

	public float getVelX() {
		return vel.getX();
	}

	public float getVelY() {
		return vel.getY();
	}

	public Vector2d getVel() {
		return vel;
	}

	public float getHalfWidth() {
		return width / 2;
	}

	public float getHalfHeight() {
		return height / 2;
	}
	
	public float getRoundedX() {
		int n = (int)(x * 10);
		float m = n / 10;
		return m;
	}
	
	public float getRoundedY() {
		int n = (int)(y * 10);
		float m = n / 10;
		return m;
	}
	
	public boolean equals(Entity e) {
		return (this.getRoundedX() == e.getRoundedX() && this.getRoundedY() == e.getRoundedY() && this.vel.getX() == e.vel.getX() && this.vel.getY() == e.vel.getY());
	}

	public void debug() {
		// if(GamePanel.FRAMES % 30 == 0)
		System.out.println("Grounded : " + grounded + ";    Vel : " + vel.getX() + ", " + vel.getY() + ";    Pos : " + x
				+ ", " + y);
	}

}
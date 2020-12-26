package entities;

import java.awt.Color;
import java.awt.Graphics2D;

import graphics.Animation;
import input.KeyHandler;
import input.MouseHandler;
import states.LevelState;
import util.Assets;
import util.Vector2d;

public class Player extends Entity {
	
	
	//GameLevelLoader COLOR
	public static final Color COLOR = Color.BLACK;

	// Sizes
	public static final int IMG_WIDTH = 64;
	public static final int IMG_HEIGHT = 64;

	public static final int WIDTH = IMG_WIDTH * 12 / 16;
	public static final int HEIGHT = IMG_HEIGHT * 15 / 16;

	// Movement
	private int img_x; // posizione che verrà usata per disegnare l'immagine effettiva
	private int img_y; // queste coordinate vengono aggiornate ogni frame in base alle coordinate
						// dell'HITBOX,
						// la quale dipende dalle coordinate 'x' e 'y'
	private float x_acc = 1f;
	
	private float air_friction = .3f;
	
	protected float g = .4f;

	// Animation things
	private final int NUM_ANIMATION = 4; // right, left, idle_right, idle_left
	private int noAnimation = -1;
	public static final int RIGHT = 0;
	public static final int LEFT = 1;
	public static final int IDLE_RIGHT = 2;
	public static final int IDLE_LEFT = 3;

	private static final int GENERAL_IDLE = -1;

	private int currentAnimation = IDLE_RIGHT;

	public Player(LevelState gs, int x, int y, int ID) {
		super(gs, x + IMG_WIDTH * 1 / 8, y + IMG_HEIGHT * 1 / 16, WIDTH, HEIGHT, ID);
		img_x = x;
		img_y = y;
		vel.setLimit(DEFAULT_LIMIT_SPEED_X, DEFAULT_LIMIT_SPEED_Y);
		sprite = Assets.PLAYER;
		initializeAnimations();
	}

	@Override
	public void update() {
		//updateOld();
		//x += vel.getX();
		//y += vel.getY();
		updateImageCoords();
		updateBounds();
		//checkGroundedAndChangeFriction();
		vel.addY(g);
		accelerateX(currentAnimation);
		if(Math.abs(vel.getY()) < DEFAULT_MINIMUM_SPEED_X) vel.setY(0);
		if(Math.abs(vel.getX()) < DEFAULT_MINIMUM_SPEED_Y) vel.setX(0);
		animations[currentAnimation].update();
		//Vector2d.centerCameraOn(this);
		//debug();
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(animations[currentAnimation].getFrame(), (int) (img_x - Vector2d.getWorldX()),
				(int) (img_y - Vector2d.getWorldY()), IMG_WIDTH, IMG_HEIGHT, null);
		g.setColor(Color.red);
		g.drawRect((int) (x - Vector2d.getWorldX()), (int) (y - Vector2d.getWorldY()), (int) width, (int) height);

	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		if (key.d.down && key.a.down) {
			changeAnimationTo(GENERAL_IDLE);
		} else if (key.d.down) {
			changeAnimationTo(RIGHT);
		} else if (key.a.down) {
			changeAnimationTo(LEFT);
		} else {
			changeAnimationTo(GENERAL_IDLE);
		}

		if (key.space.down && grounded) {
			vel.setY(-jumpPower);
		}
	}

	public void accelerateX(int direction) {
		if (direction == RIGHT) { // in base alla direzione accellero il player verso di essa in base all'attrito
			vel.addX(x_acc * (grounded ? friction : air_friction));
			if (vel.getX() > maxSpeedX)
				vel.setX(maxSpeedX);
		} else if (direction == LEFT) {
			vel.addX(-x_acc * (grounded ? friction : air_friction));
			if (vel.getX() < -maxSpeedX)
				vel.setX(-maxSpeedX);
		} else if (direction == GENERAL_IDLE || direction == IDLE_LEFT || direction == IDLE_RIGHT) { // il player si deve fermare, quindi l'accellerazione contrasta la velocità
			if (vel.getX() > 0) { // del player, mantenendo sempre la 'friction' del blocco su cui si trova
				vel.addX(-x_acc * (grounded ? friction : air_friction));
				if (vel.getX() <= 0)
					vel.resetX();
			} else if (vel.getX() < 0) {
				vel.addX(x_acc * (grounded ? friction : air_friction));
				if (vel.getX() > 0)
					vel.resetX();
			}
		}
	}

	private void changeAnimationTo(int animation) {
		if (animation == GENERAL_IDLE) {
			if (animation != IDLE_RIGHT && animation != IDLE_LEFT) {
				if (currentAnimation == RIGHT)
					currentAnimation = IDLE_RIGHT;
				else if (currentAnimation == LEFT)
					currentAnimation = IDLE_LEFT;
			}
		} else
			currentAnimation = animation;

		for (int i = 0; i < NUM_ANIMATION; i++) {
			if (i != currentAnimation) {
				animations[i].resetAnimation();
			}
		}
	}

	private void initializeAnimations() {
		animations = new Animation[NUM_ANIMATION];
		animations[RIGHT] = new Animation(sprite.getSpriteArray(RIGHT), DEFAULT_ANIMATION_DELAY);
		animations[LEFT] = new Animation(sprite.getSpriteArray(LEFT), DEFAULT_ANIMATION_DELAY);
		animations[IDLE_RIGHT] = new Animation(sprite.getSpriteArray(IDLE_RIGHT), noAnimation);
		animations[IDLE_LEFT] = new Animation(sprite.getSpriteArray(IDLE_LEFT), noAnimation);
	}

	public void updateImageCoords() {
		img_x = (int) (x - IMG_WIDTH * 1 / 8);
		img_y = (int) (y - IMG_HEIGHT * 1 / 16);
	}

}

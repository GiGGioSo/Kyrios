package states;

import java.awt.Graphics2D;

import input.KeyHandler;
import input.MouseHandler;
import util.User;

public abstract class GameState {

	protected GameStateManager gsm;
	
	User user;
	
	protected int delay;
	protected int waitTime = 20;
	
	protected boolean operative;

	public GameState(GameStateManager gsm, User user) {
		this.gsm = gsm;
		this.user = user;
	}
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		this.user = null;
	}

	public abstract void update();

	public abstract void draw(Graphics2D g);

	public abstract void input(MouseHandler mouse, KeyHandler key);

}

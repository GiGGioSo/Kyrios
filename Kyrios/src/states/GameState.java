package states;

import java.awt.Graphics2D;
import input.*;

public abstract class GameState {

	protected GameStateManager gsm;
	
	protected int delay;
	protected int waitTime = 20;
	
	protected boolean operative;

	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public abstract void update();

	public abstract void draw(Graphics2D g);

	public abstract void input(MouseHandler mouse, KeyHandler key);

}

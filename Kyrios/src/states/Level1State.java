package states;

import java.awt.Graphics2D;

import input.KeyHandler;
import input.MouseHandler;
import main.GamePanel;
import ui.InGameMenu;
import ui.MiniMap;
import util.User;

public class Level1State extends LevelState {

	public Level1State(GameStateManager gsm, User user) {
		super(gsm, user);
		LEVEL = 1;
		createLevel(LEVEL);
		gll.loadLevel(this);
		menu = new InGameMenu(this);
		map = new MiniMap(GamePanel.WIDTH, 0, 200, this, "TOPRIGHT");
		mainPlayer = (int) (Math.random() * gll.getEntitiesSize());
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		super.input(mouse, key);
	}

}

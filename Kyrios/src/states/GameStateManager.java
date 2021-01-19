package states;

import java.awt.Graphics2D;

import input.KeyHandler;
import input.MouseHandler;
import main.GamePanel;
import util.Assets;
import util.User;

public class GameStateManager {

	public static enum States { // mettere come indice più alto quello che deve essere disegnato sopra tutto
		LEVEL1			(0, true), 
		LEVEL2			(1, true), 
		LEVELSELECTOR	(2, true), 
		MENU			(3, true),
		LOGIN			(4, true);

		public int ID;
		public boolean toUpdate;
		public boolean toDraw;
		public boolean toInput;

		States(int ID, boolean u, boolean d, boolean i) {
			this.ID = ID;
			toUpdate = u;
			toDraw = d;
			toInput = i;
		}

		States(int ID, boolean all) {
			this(ID, all, all, all);
		}
		
		public static void to(boolean u, boolean d, boolean i, int x) {
			if (x == LEVEL1.ID) {
				LEVEL1.toUpdate = u;
				LEVEL1.toDraw = d;
				LEVEL1.toInput = i;
			} else if (x == LEVEL2.ID) {
				LEVEL2.toUpdate = u;
				LEVEL2.toDraw = d;
				LEVEL2.toInput = i;
			} else if (x == MENU.ID) {
				MENU.toUpdate = u;
				MENU.toDraw = d;
				MENU.toInput = i;
			} else if (x == LEVELSELECTOR.ID) {
				LEVELSELECTOR.toUpdate = u;
				LEVELSELECTOR.toDraw = d;
				LEVELSELECTOR.toInput = i;
			} else if (x == LOGIN.ID) {
				LOGIN.toUpdate = u;
				LOGIN.toDraw = d;
				LOGIN.toInput = i;
			}
		}

		public static States get(int i) {
			if (i == LEVEL1.ID) {
				return LEVEL1;
			} else if (i == LEVEL2.ID) {
				return LEVEL2;
			} else if (i == MENU.ID) {
				return MENU;
			} else if (i == LEVELSELECTOR.ID) {
				return LEVELSELECTOR;
			} else if (i == LOGIN.ID) {
				return LOGIN;
			}

			return null;
		}
	}
	
	public static States L1 = States.LEVEL1;
	public static States L2 = States.LEVEL2;
	public static States MENU = States.MENU;
	public static States LSELECTOR = States.LEVELSELECTOR;
	public static States LOGIN = States.LOGIN;

	GamePanel gp;
	
	GameState[] states;
	
	User user;

	public GameStateManager(GamePanel gp) {
		this.gp = gp;
		Assets.loadSprites();
		states = new GameState[5];

		//states[MENU.ID] = new MenuState(this, user);
		states[LOGIN.ID] = new RegistrationState(this);
	}

	public void update() {
		for (int i = 0; i < states.length; i++) {
			if (states[i] == null)
				continue;
			if (States.get(i).toUpdate)
				states[i].update();
		}

	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < states.length; i++) {
			if (states[i] == null)
				continue;
			if (States.get(i).toDraw)
				states[i].draw(g);
		}
	}

	public void input(MouseHandler mouse, KeyHandler key) {
		for (int i = 0; i < states.length; i++) {
			if (states[i] == null)
				continue;
			if (States.get(i).toInput)
				states[i].input(mouse, key);
		}
	}

	public void createState(int i) {
		if (i == L1.ID) {
			if (states[L1.ID] == null)
				states[L1.ID] = new Level1State(this, user);
		} else if (i == L2.ID) {
			if (states[L2.ID] == null)
				states[L2.ID] = new Level2State(this, user);
		} else if (i == MENU.ID) {
			if (states[MENU.ID] == null)
				states[MENU.ID] = new MenuState(this, user);
		} else if (i == LSELECTOR.ID) {
			if (states[LSELECTOR.ID] == null)
				states[LSELECTOR.ID] = new LevelSelectorState(this, user);
		}
	}

	public void deleteState(int i) {
		if (i == L1.ID) {
			if (states[L1.ID] != null)
				states[L1.ID] = null;
		} else if (i == L2.ID) {
			if (states[L2.ID] != null)
				states[L2.ID] = null;
		} else if (i == MENU.ID) {
			if (states[MENU.ID] != null)
				states[MENU.ID] = null;
		} else if (i == LSELECTOR.ID) {
			if (states[LSELECTOR.ID] != null)
				states[LSELECTOR.ID] = null;
		}
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public GamePanel getPanel() {
		return gp;
	}

}

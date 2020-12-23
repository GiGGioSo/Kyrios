package states;

import java.awt.Graphics2D;

import input.KeyHandler;
import input.MouseHandler;
import util.Assets;

public class GameStateManager {

	public static enum States { // mettere come indice più alto quello che deve essere disegnato sopra tutto
		LEVEL1			(0, true), 
		LEVEL2			(1, true), 
		LEVELSELECTOR	(2, true), 
		MENU			(3, true);

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
			}

			return null;
		}
	}
	
	public static States L1 = States.LEVEL1;
	public static States L2 = States.LEVEL2;
	public static States MENU = States.MENU;
	public static States LSELECTOR = States.LEVELSELECTOR;

	GameState[] states;

	public GameStateManager() {
		Assets.loadSprites();
		states = new GameState[4];

		states[MENU.ID] = new MenuState(this);
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
				states[L1.ID] = new Level1State(this);
		} else if (i == L2.ID) {
			if (states[L2.ID] == null)
				states[L2.ID] = new Level2State(this);
		} else if (i == MENU.ID) {
			if (states[MENU.ID] == null)
				states[MENU.ID] = new MenuState(this);
		} else if (i == LSELECTOR.ID) {
			if (states[LSELECTOR.ID] == null)
				states[LSELECTOR.ID] = new LevelSelectorState(this);
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

}

package states;

import java.awt.Graphics2D;

import graphics.Button;
import input.KeyHandler;
import input.MouseHandler;
import main.GamePanel;
import util.Assets;

public class MenuState extends GameState {
	
	private Button play;
	private Button levels;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		createButtons();
		allButtonsUnpressed();
		delay = 0;
	}

	@Override
	public void update() {
		if(delay > waitTime) operative = true;
		else {
			operative = false;
			delay++;
		}
		play.update();
		levels.update();
		if(play.isPressed() && operative) {
			if(LevelSelectorState.LEVEL_SELECTED == 1) {
				gsm.createState(GameStateManager.L1.ID);
			} else if(LevelSelectorState.LEVEL_SELECTED == 2) {
				gsm.createState(GameStateManager.L2.ID);
			}
			gsm.deleteState(GameStateManager.MENU.ID);
		} else if(levels.isPressed() && operative) {
			gsm.createState(GameStateManager.LSELECTOR.ID);
			gsm.deleteState(GameStateManager.MENU.ID);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		play.draw(g);
		levels.draw(g);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		play.input(mouse, key);
		levels.input(mouse, key);
	}
	
	public void allButtonsUnpressed() {
		play.setPressed(false);
		levels.setPressed(false);
	}
	
	private void createButtons() {
		play = new Button(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2 - 50, Assets.BUTTON_PLAY_INACTIVE, Assets.BUTTON_PLAY_ACTIVE, "CENTERBOTTOM");
		levels = new Button(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2 + 50, Assets.BUTTON_LIVELLO_INACTIVE, Assets.BUTTON_LIVELLO_ACTIVE, "CENTERTOP");
	}

}

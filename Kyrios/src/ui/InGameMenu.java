package ui;

import java.awt.Graphics2D;

import graphics.Button;
import input.KeyHandler;
import input.MouseHandler;
import main.GamePanel;
import states.GameStateManager;
import states.LevelState;
import util.Assets;

public class InGameMenu {
	
	private LevelState level;
	
	private boolean isOn;
	
	private Button resume;
	private Button quit;
	
	private int delay;
	private int waitTime = 20;
	private boolean operative;

	public InGameMenu(LevelState level) {
		delay = 0;
		operative = false;
		createButtons();
		this.level = level;
		isOn = false;
	}


	public void update() {
		if(!isOn) return;
		if(delay > waitTime) operative = true;
		else {
			allButtonsUnpressed();
			operative = false;
			delay++;
		}
		resume.update();
		quit.update();
		if(resume.isPressed() && operative) {
			setOff();
		} else if (quit.isPressed() && operative) {
			if(level.LEVEL == 1) {
				level.getGSM().deleteState(GameStateManager.L1.ID);	
			} else if (level.LEVEL == 2) {
				level.getGSM().deleteState(GameStateManager.L2.ID);
			}
			level.getGSM().createState(GameStateManager.MENU.ID);
		}
	}

	public void draw(Graphics2D g) {
		if(!isOn) return;
		resume.draw(g);
		quit.draw(g);
	}


	public void input(MouseHandler mouse, KeyHandler key) { 
		if(!isOn) return;
		resume.input(mouse, key);
		quit.input(mouse, key);
	}
	
	private void allButtonsUnpressed() {
		resume.setPressed(false);
		quit.setPressed(false);
	}
	
	private void createButtons() {
		resume = new Button(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2 - 50, 400, 150, Assets.GAME_OVER, Assets.GAME_OVER, "CENTERBOTTOM");
		quit = new Button(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2 + 50, Assets.BUTTON_BACK_INACTIVE, Assets.BUTTON_BACK_ACTIVE, "CENTERTOP");
	}
	
	public boolean isOn() {
		return isOn;
	}
	
	public void setOn() {
		isOn = true;
		delay = 0;
	}
	
	public void setOff() {
		isOn = false;
	}
}

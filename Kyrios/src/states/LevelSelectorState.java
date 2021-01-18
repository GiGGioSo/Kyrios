package states;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import graphics.Button;
import input.KeyHandler;
import input.MouseHandler;
import main.GamePanel;
import util.Assets;
import util.User;

public class LevelSelectorState extends GameState {

	public static int LEVEL_SELECTED = 1;
	
	private boolean showPreview;
	
	private int preview_x, preview_y;

	private Button l1;
	private Button l2;
	
	private BufferedImage current_preview;

	public LevelSelectorState(GameStateManager gsm, User user) {
		super(gsm, user);
		createButtons();
		allButtonsUnpressed();
		delay = 0;
		operative = false;
	}

	@Override
	public void update() {
		if(delay > waitTime) operative = true;
		else {
			operative = false;
			delay++;
		}
		l1.update();
		l2.update();
		if (l1.isPressed() && operative) {
			LEVEL_SELECTED = 1;
			gsm.deleteState(GameStateManager.LSELECTOR.ID);
			gsm.createState(GameStateManager.MENU.ID);
		} else if (l2.isPressed() && operative) {
			LEVEL_SELECTED = 2;
			gsm.deleteState(GameStateManager.LSELECTOR.ID);
			gsm.createState(GameStateManager.MENU.ID);
		}

	}

	@Override
	public void draw(Graphics2D g) {
		l1.draw(g);
		l2.draw(g);
		if(showPreview) {
			if(current_preview.getWidth() > current_preview.getHeight()) {
				g.drawImage(current_preview, preview_x, preview_y, 250, 250*current_preview.getHeight() / current_preview.getWidth(), null);
			} else {
				g.drawImage(current_preview, preview_x, preview_y, 250*current_preview.getWidth() / current_preview.getHeight(), 250, null);
			}
		}
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		l1.input(mouse, key);
		l2.input(mouse, key);

		if (l1.getTimeOnButton() > 80) {
			showPreview = true;
			preview_x = mouse.getX();
			preview_y = mouse.getY();
			current_preview = Assets.LEVEL_1;
		} else if (l2.getTimeOnButton() > 80) {
			showPreview = true;
			preview_x = mouse.getX();
			preview_y = mouse.getY();
			current_preview = Assets.LEVEL_2;
		} else {
			showPreview = false;
		}
	}

	public void createButtons() {
		l1 = new Button(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2 - 50, Assets.BUTTON_LEVEL1_INACTIVE,
				Assets.BUTTON_LEVEL1_ACTIVE, "CENTERBOTTOM");
		l2 = new Button(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2 + 50, Assets.BUTTON_LEVEL2_INACTIVE,
				Assets.BUTTON_LEVEL2_ACTIVE, "CENTERTOP");
	}
	
	private void allButtonsUnpressed() {
		l1.setPressed(false);
		l2.setPressed(false);
	}
}

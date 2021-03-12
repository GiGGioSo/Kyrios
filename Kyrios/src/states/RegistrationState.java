package states;

import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JPanel;
import javax.swing.JTextField;

import graphics.Button;
import input.KeyHandler;
import input.MouseHandler;
import main.GamePanel;
import util.Assets;
import util.User;

public class RegistrationState extends GameState{
	
	User user;
	
	JPanel panel;
	
	JTextField usernameField;
	Button enter;

	public RegistrationState(GameStateManager gsm) {
		super(gsm);
		delay = 0;
		user = null;
		createComponentsAndButtons();
	}

	@Override
	public void update() {
		if(delay > waitTime) operative = true;
		else {
			operative = false;
			delay++;
		}
		enter.update();
		if(enter.isPressed() && operative) {
			String username = usernameField.getText();
			if(user == null) user = User.loadUserInfo(username, GamePanel.SAVE_PATH + "\\" + username +".ser");
		}
	}

	@Override
	public void draw(Graphics2D g) {
		enter.draw(g);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		enter.input(mouse, key);
	}
	
	private void createComponentsAndButtons() {
		usernameField = new JTextField(20);
		usernameField.setBounds(GamePanel.WIDTH/3, GamePanel.HEIGHT/3, 150, 30);
		gsm.getPanel().add(usernameField);
		
		enter = new Button(GamePanel.WIDTH/3, GamePanel.HEIGHT/3 * 2, Assets.BUTTON_PLAY_INACTIVE, Assets.BUTTON_PLAY_ACTIVE, "TOPLEFT");
	}
	
	public void allButtonsUnpressed() {
		enter.setPressed(false);
	}

}

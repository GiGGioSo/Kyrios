package states;

import java.awt.Graphics2D;

import javax.swing.JTextField;

import graphics.Button;
import input.KeyHandler;
import input.MouseHandler;
import main.GamePanel;
import util.User;

public class RegistrationState extends GameState{
	
	User user;
	
	JTextField usernameField;
	Button enter;

	public RegistrationState(GameStateManager gsm) {
		super(gsm);
		createComponents();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics2D g) {
		
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		
	}
	
	private void createComponents() {
		usernameField = new JTextField(20);
		usernameField.setBounds(GamePanel.WIDTH/3, GamePanel.HEIGHT/3, 150, 30);
		gsm.getPanel().add(usernameField);
	}

}

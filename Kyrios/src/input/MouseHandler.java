package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class MouseHandler implements MouseListener, MouseMotionListener{
	
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	
	public MouseHandler(GamePanel game) {
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton(); // 1 : leftMouseButton;		2 : middleMouseButton;	3 : rightMouseButton;		
	}

	@Override
	public void mouseReleased(MouseEvent e) { 
		mouseB = -1;
	}
	
	//Getters
	public int getX() { return mouseX; }
	public int getY() { return mouseY; }
	public int getButton() { return mouseB; }

}

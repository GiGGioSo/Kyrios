package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import main.GamePanel;

public class KeyHandler implements KeyListener{
	
	public ArrayList<Key> keys = new ArrayList<Key>();
	
	public Key w = new Key();
	public Key s = new Key();
	public Key a = new Key();
	public Key d = new Key();
	public Key o = new Key();
	public Key p = new Key();
	public Key y = new Key();
	public Key space = new Key();
	public Key esc = new Key();
	public Key shift = new Key();
	
	public KeyHandler(GamePanel game) {
		game.addKeyListener(this);
		game.setFocusable(true);
	}
	
	public void releaseAll() {
		for(int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}
	
	public void tick() {
		for(int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
	}
	
	public void toggle(KeyEvent e, boolean pressed) {
		if(e.getKeyCode() == KeyEvent.VK_W) { w.toggle(pressed); }
		if(e.getKeyCode() == KeyEvent.VK_S) { s.toggle(pressed); }
		if(e.getKeyCode() == KeyEvent.VK_A) { a.toggle(pressed); }
		if(e.getKeyCode() == KeyEvent.VK_D) { d.toggle(pressed); }
		if(e.getKeyCode() == KeyEvent.VK_O) { o.toggle(pressed); }
		if(e.getKeyCode() == KeyEvent.VK_P) { p.toggle(pressed); }
		if(e.getKeyCode() == KeyEvent.VK_Y) { y.toggle(pressed); }
		if(e.getKeyCode() == KeyEvent.VK_SPACE) { space.toggle(pressed); }
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) { esc.toggle(pressed); }
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) { shift.toggle(pressed); }
	}

	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggle(e, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	//INNER CLASS
	public class Key {

		public int presses, absorbs;
		public boolean down, clicked;
		
		public Key() {
			keys.add(this);
		}
		
		public void toggle(boolean pressed) {
			if(down != pressed) {
				down = pressed;
			}
			if(pressed) {
				presses++;
			}
		}
		
		public void tick() {
			if(absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}
	//END INNER CLASS
}

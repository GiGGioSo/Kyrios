package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import input.KeyHandler;
import input.MouseHandler;
import util.RectangleP;

public class Button extends RectangleP {
	
	private int ratio;
	
	private int timeOnButton;

	private BufferedImage default_texture;
	private BufferedImage pressed_texture;
	
	private boolean pressed;
	private boolean mouseOnButton;
	
	public Button(float x, float y, float width, float height, BufferedImage default_texture, BufferedImage pressed_texture, String posRelativeTo) {
		super(x, y, width, height);
		this.default_texture = default_texture;
		this.pressed_texture = pressed_texture;
		pressed = false;
		mouseOnButton = false;
		ratio = default_texture.getWidth() / default_texture.getHeight();
		if (posRelativeTo.equals("TOPLEFT")) {
			this.x = x;
			this.y = y;
		} else if (posRelativeTo.equals("TOPRIGHT")) {
			this.x = x - width;
			this.y = y;
		} else if (posRelativeTo.equals("BOTTOMLEFT")) {
			this.x = x;
			this.y = y - height;
		} else if (posRelativeTo.equals("BOTTOMRIGHT")) {
			this.x = x - width;
			this.y = y - height;
		} else if (posRelativeTo.equals("CENTERRIGHT")) {
			this.x = x - width;
			this.y = y - height / 2;
		} else if (posRelativeTo.equals("CENTERLEFT")) {
			this.x = x;
			this.y = y - height / 2;
		} else if (posRelativeTo.equals("CENTERTOP")) {
			this.x = x - width / 2;
			this.y = y;
		} else if (posRelativeTo.equals("CENTERBOTTOM")) {
			this.x = x - width / 2;
			this.y = y - height;
		} else if (posRelativeTo.equals("CENTER")) {
			this.x = x - width / 2;
			this.y = y - height / 2;
		}
	}
	
	public Button(float x, float y, BufferedImage default_texture, BufferedImage pressed_texture, String posRelativeTo) {
		this(x, y, (float)default_texture.getWidth(), (float)default_texture.getHeight(), default_texture, pressed_texture, posRelativeTo);
	}
	
	public void update() {
		float ox = x;
		float oy = y;
		float ow = width;
		float oh = height;
		if(mouseOnButton) {
			this.setHeightKeepingRatio(default_texture.getHeight() * 11/10);
			this.setCoords((int)(ox + ow/2), (int)(oy + oh/2), "CENTER");
		} else {
			this.setHeightKeepingRatio(default_texture.getHeight());
			this.setCoords((int)(ox + ow/2), (int)(oy + oh/2), "CENTER");
		}
	}
	
	public void draw(Graphics2D g) {
		if(mouseOnButton)
			g.drawImage(pressed_texture, (int)x, (int)y, (int)width, (int)height, null);
		else
			g.drawImage(default_texture, (int)x, (int)y, (int)width, (int)height, null);
	}
	
	public void input(MouseHandler mouse, KeyHandler key) {
		if(this.contains(mouse.getX(), mouse.getY())) {
			mouseOnButton = true;
			timeOnButton++;
			if(mouse.getButton() == 1) {
				pressed = true;
			} else {
				pressed = false;
			}
		} else {
			mouseOnButton = false;
			timeOnButton = 0;
		}
	}
	
	public void setWidthKeepingRatio(float width) {
		this.width = width;
		this.height = width / ratio;
	}
	
	public void setHeightKeepingRatio(float height) {
		this.height = height;
		this.width = height * ratio;
	}
	
	public void setDimensions(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	public int getTimeOnButton() {
		return timeOnButton;
	}

}

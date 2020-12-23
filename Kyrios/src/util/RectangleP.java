package util;

import java.awt.Point;
import java.awt.Rectangle;

public class RectangleP {
	
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	protected float right;
	protected float down;
	
	protected float ox;
	protected float oy;
	protected float oright;
	protected float odown;

	public RectangleP(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		updateBounds();
	}
	
	public RectangleP(RectangleP rec) {
		this(rec.x, rec.y, rec.width, rec.height);
	}
	
	public RectangleP() {
		this(0, 0, 0, 0);
	}


	public boolean doesItCollideWith(RectangleP rec) {
		return (x < rec.x + rec.width &&
			x + width > rec.x &&
			y < rec.y + rec.height &&
			y + height > rec.y);
	}
	
	@Deprecated
	public RectangleP getWhatCollidesWith(RectangleP r) {
		//'this' sta fermo   //'r' si muove
		if(!this.doesItCollideWith(r)) return null;
		
		float cX = 0, cY = 0, cW = 0, cH = 0;
		
		if(r.right < this.right && r.x < this.x) { // 'r' è sulla sinistra di 'this'
			cX = this.x;
			cW = r.right - this.x;
			if(r.y < this.y && r.down < this.down) { // 'r' interseca sullo spigolo basso dx
				cY = this.y;
				cH = r.down - this.y;
			} else if(r.y > this.y && r.down < this.down) {
				cY = r.y;
				cH = r.height;
			} else if(r.y > this.y && r.down > this.down ) {
				cY = r.y;
				cH = this.down - r.y;
			}
		} else if(r.right > this.right && r.x > this.x){ // 'r' è sulla destra di 'this'
			cX = r.x;
			cW = this.right - r.x;
			if(r.y < this.y && r.down < this.down) { // 'r' interseca sullo spigolo basso dx
				cY = this.y;
				cH = r.down - this.y;
			} else if(r.y > this.y && r.down < this.down) {
				cY = r.y;
				cH = r.height;
			} else if(r.y > this.y && r.down > this.down ) {
				cY = r.y;
				cH = this.down - r.y;
			}
		}
		
		return new RectangleP(cX, cY, cW, cH);
	}
	
	public boolean contains(float x, float y) {
		return new Rectangle((int)this.x, (int)this.y, (int)this.width, (int)this.height).contains(new Point((int)x, (int)y));
	}
	
	protected void updateOld() {
		ox = x;
		oy = y;
		oright = right;
		odown = down;
	}
	
	protected void updateBounds() {
		right = x + width;
		down = y + height;
	}
	
	// GETTERS AND SETTERS
	public float getORight() {
		return oright;
	}
	
	public float getODown() {
		return odown;
	}
	
	public float getRight() {
		return right;
	}

	public float getDown() {
		return this.down;
	}

	public float getOx() {
		return ox;
	}

	public float getOy() {
		return oy;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setRight(float right) {
		this.right = right;
		this.x = right - width;
	}
	
	public void setDown(float down) {
		this.down = down;
		y = down - height;
	}
	
	public void setCoords(int x, int y) {
		this.x = x; 
		this.y = y; 
	}
	
	public void setCoords(int x, int y, String posRelativeTo) {
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
	
	public void addX(float i) {
		this.x += i;
	}
	
	public void addY(float i) {
		this.y += i;
	}
	
}

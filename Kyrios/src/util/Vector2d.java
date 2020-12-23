package util;

import entities.Entity;
import main.GamePanel;

public class Vector2d {
	
	private float x;
	private float y;
	
	private float limitX, limitY; // meccanismo ancora da implementare
	
	private static float worldX;
	private static float worldY;
	
	private static float HORIZONTAL_INTERPOLATION = 0.05f;
	private static float VERTICAL_INTERPOLATION = 0.025f;
	
	public Vector2d() {
		x = 0;
		y = 0;
	}
	
	public Vector2d(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2d(Vector2d vec) {
		this(vec.x, vec.y);
	}
	
	public void setLimit(float limitX, float limitY) { 
		this.limitX = limitX;
		this.limitY = limitY;
	}
	
	public float getLimitX() { return limitX; }
	public float getLimitY() { return limitY; }
	
	public void addX(float i) {
		x += i; 
		if(x > limitX) x = limitX;
		if(x < -limitX) x = -limitX;
	}
	public void addY(float i) {
		y += i; 
		if(y > limitY) y = limitY;
		if(y < -limitY) y = -limitY;
	}
	public void add(Vector2d vec) {
		addX(vec.getX());
		addY(vec.getY());
	}
	
	public void setX(float i) { x = i; }
	public void setY(float i) { y = i; }
	public float getX() { return x; }
	public float getY() { return y; }
	
	public void resetX() { x = 0; }
	public void resetY() { y = 0; }
	public void reset() { x = 0; y = 0; }
	
	public void setVector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector(Vector2d vec) {
		x = vec.x;
		y = vec.y;
	}
	
	//World methods
	public void setWorldCoords(float x, float y) {
		worldX = x;
		worldY = y;
	}
	
	public static void addWorldX(float i) { worldX += i; }
	public static void addWorldY(float i) { worldX += i; }
	
	public static float getWorldX() { return worldX; }
	public static float getWorldY() { return worldY; }
	
	public static void centerCameraOn(Entity e) {
		worldX = Util.interpolate(worldX, e.getX() - GamePanel.WIDTH/2 + e.getHalfWidth(), HORIZONTAL_INTERPOLATION);	
		worldY = Util.interpolate(worldY, e.getY() - GamePanel.HEIGHT/2 + e.getHalfHeight(), VERTICAL_INTERPOLATION);
	}
	
	public Vector2d getWorldDistances() {
		return new Vector2d(x - worldX, y - worldY);
	}

}

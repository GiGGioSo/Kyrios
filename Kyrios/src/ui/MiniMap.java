package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import blocks.Block;
import blocks.Dirt;
import blocks.Ice;
import entities.Entity;
import entities.Player;
import states.LevelState;
import util.Vector2d;

public class MiniMap {
	
	private boolean visible;

	private LevelState level;
	private Vector2d offSet;

	ArrayList<Entity> entities;

	private float width, height;

	private static final int SIZE = 8;

	private Color[][] colorMap;

	private int x, y;
	
	private Vector2d pos[];

	private int visibility;

	public MiniMap(int xOffSet, int yOffSet, int visibility, LevelState level, String posRelativeTo) {
		visible = false;
		this.level = level;
		this.entities = this.level.getEntities();
		this.visibility = visibility;
		pos = new Vector2d[entities.size()];
		colorMap = level.getMap().getColorMap();
		x = colorMap.length;
		y = colorMap[0].length;
		width = x * SIZE;
		height = y * SIZE;
		if (posRelativeTo.equals("TOPLEFT")) {
			offSet = new Vector2d(xOffSet, yOffSet);
		} else if (posRelativeTo.equals("TOPRIGHT")) {
			offSet = new Vector2d(xOffSet - width, yOffSet);
		} else if (posRelativeTo.equals("BOTTOMLEFT")) {
			offSet = new Vector2d(xOffSet, yOffSet - height);
		} else if (posRelativeTo.equals("BOTTOMRIGHT")) {
			offSet = new Vector2d(xOffSet - width, yOffSet - height);
		} else if (posRelativeTo.equals("CENTERRIGHT")) {
			offSet = new Vector2d(xOffSet - width, yOffSet - height / 2);
		} else if (posRelativeTo.equals("CENTERLEFT")) {
			offSet = new Vector2d(xOffSet, yOffSet - height / 2);
		} else if (posRelativeTo.equals("CENTERTOP")) {
			offSet = new Vector2d(xOffSet - width / 2, yOffSet);
		} else if (posRelativeTo.equals("CENTERBOTTOM")) {
			offSet = new Vector2d(xOffSet - width / 2, yOffSet - height);
		} else if (posRelativeTo.equals("CENTER")) {
			offSet = new Vector2d(xOffSet - width / 2, yOffSet - height / 2);
		}
		for(int i = 0; i < pos.length; i++) {
			pos[i] = new Vector2d();
		}
	}

	public void update() {
		if(visible) return;
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) == null) continue;
			pos[i].setX((int)(entities.get(i).getX() / Block.SIZE));
			pos[i].setY((int)(entities.get(i).getY() / Block.SIZE));
		}
	}

	public void draw(Graphics2D g) {
		if(!visible) return;
		g.setColor(new Color(150, 150, 150, visibility));
		g.fillRect((int)offSet.getX(), (int)offSet.getY(), (int)width, (int)height);
		drawBlocks(g);
		drawEntities(g);
	}
	
	private void drawBlocks(Graphics2D g) {
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				Color c = colorMap[i][j];
				if (c.equals(Dirt.COLOR)) {
					g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), visibility));
					g.fillRect((int)(offSet.getX() + i * SIZE), (int)(offSet.getY() + j * SIZE), SIZE, SIZE);
				} else if (c.equals(Ice.COLOR)) {
					g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), visibility));
					g.fillRect((int)(offSet.getX() + i * SIZE), (int)(offSet.getY() + j * SIZE), SIZE, SIZE);
				}
			}
		}
	}
	
	private void drawEntities(Graphics2D g) {
		Color pc = Player.COLOR;
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) == null) continue;
			g.setColor(new Color(pc.getRed(), pc.getGreen(), pc.getBlue(), visibility));
			g.fillRect((int)(offSet.getX() + pos[i].getX() * SIZE), (int)(offSet.getY() + pos[i].getY() * SIZE), SIZE, SIZE);
		}
	}
	
	public void setVisibility(int i) {
		visibility = i;
	}
	
	public void setVisible(boolean vis) {
		if(visible != vis) visible = vis;
	}
	
	public void toggleVisible() {
		if(visible) visible = false;
		else if(!visible) visible = true;
	}

}

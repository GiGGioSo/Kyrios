package states;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import blocks.Block;
import entities.Entity;
import input.KeyHandler;
import input.MouseHandler;
import main.GamePanel;
import ui.InGameMenu;
import ui.MiniMap;
import util.GameLevelLoader;
import util.Vector2d;

public class LevelState extends GameState {
	
	public int LEVEL;

	protected MiniMap map;
	
	protected InGameMenu menu;
	
	protected GameLevelLoader gll;

	protected int mainPlayer;

	protected ArrayList<Entity> entities = new ArrayList<>();
	protected ArrayList<Block> blocks = new ArrayList<>();
	
	public LevelState(GameStateManager gsm) {
		super(gsm);
	}

	protected void createLevel(int level) {
		gll = new GameLevelLoader(level);
	}

	@Override
	public void update() {
		if(menu.isOn()) {
			menu.update();
		} else {
			entities.trimToSize();
			blocks.trimToSize();
			makeCollisions();
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).update();
				if (entities.get(i).ID == mainPlayer)
					Vector2d.centerCameraOn(entities.get(i));
			}
			for (int i = 0; i < blocks.size(); i++) {
				blocks.get(i).update();
			}
			eliminatePlayers();
			map.update();
			
			if(entities.size() == 1) { // Aggiungere il GAMEOVER STATE
				if(LEVEL == 1) {
					gsm.deleteState(GameStateManager.L1.ID);	
				} else if (LEVEL == 2) {
					gsm.deleteState(GameStateManager.L2.ID);
				}
				gsm.createState(GameStateManager.MENU.ID);
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(new Color(100));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) != null) {
				entities.get(i).draw(g);
			}
		}
		for (int i = 0; i < blocks.size(); i++) {
			if (blocks.get(i) != null) {
				blocks.get(i).draw(g);
			}
		}
		map.draw(g);
		menu.draw(g);
	}

	@Override
	public void input(MouseHandler mouse, KeyHandler key) {
		if(menu.isOn()) {
			menu.input(mouse, key);
		} else {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i) != null)
					entities.get(i).input(mouse, key);
			}
			
			if(key.p.clicked) { // MOMENTANEO
				gsm.deleteState(GameStateManager.L1.ID);
				gsm.createState(GameStateManager.MENU.ID);
			}
	
			if (key.shift.down)
				map.setVisible(true);
			else
				map.setVisible(false);
			
			if(key.esc.clicked) {
				menu.setOn();
			}
		}
	}
	
	protected void makeCollisions() {
		for(Entity e : entities) {
			e.collideWithBlocks(blocks);
		}
		
	}
	
	protected void eliminatePlayers() {
		for (int i = 0; i < entities.size() - 1; i++) {
			for (int j = i + 1; j < entities.size(); j++) {
				if (entities.get(i) != null && entities.get(j) != null) {
					if (entities.get(i).equals(entities.get(j))) {
						if (entities.get(i).ID == mainPlayer) {
							entities.remove(j);
						} else {
							entities.remove(i);
						}
						break;
					}
				}
			}
		}
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public int getEntitiesSize() {
		return entities.size();
	}

	public Entity getEntity(int i) {
		return entities.get(i);
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public int getBlockSize() {
		return blocks.size();
	}

	public Block getBlock(int i) {
		return blocks.get(i);
	}
	
	public void addBlock(Block b) {
		blocks.add(b);
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public GameLevelLoader getMap() {
		return gll;
	}
	
	public GameStateManager getGSM() {
		return gsm;
	}

}

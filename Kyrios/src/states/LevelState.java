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
import util.User;
import util.Vector2d;

public class LevelState extends GameState {
	
	public int LEVEL;

	protected MiniMap map;
	
	protected int timeBeforeChangingPlayer = 120;
	private int waitingForChange = 0;
	private boolean canChange = false;
	
	protected InGameMenu menu;
	
	protected GameLevelLoader gll;

	protected int mainPlayer;

	protected ArrayList<Entity> entities = new ArrayList<>();
	protected ArrayList<Block> blocks = new ArrayList<>();
	
	public LevelState(GameStateManager gsm, User user) {
		super(gsm, user);
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
			
			
			if(waitingForChange < timeBeforeChangingPlayer) {
				waitingForChange++;
			} else {
				canChange = true;
			}
			
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
	
			if (key.shift.down)
				map.setVisible(true);
			else
				map.setVisible(false);
			
			if (key.q.down && !key.r.down) {
				changeMainPlayer(-1);
			} else if (!key.q.down && key.r.down) {
				changeMainPlayer(1);
			}
			
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

	private void reOrganizePlayers(int n) { // reimposto gli ID dei player per non avere dei buchi
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).ID > n) {
				if(entities.get(i).ID == mainPlayer) mainPlayer--;
				entities.get(i).ID--;
			}
		}
		entities.trimToSize();
	}
	
	protected void eliminatePlayers() {
		for (int i = 0; i < entities.size() - 1; i++) {
			for (int j = i + 1; j < entities.size(); j++) {
				if (entities.get(i) != null && entities.get(j) != null) {
					if (entities.get(i).equals(entities.get(j))) {
						if (entities.get(i).ID == mainPlayer) {
							entities.remove(j);
							reOrganizePlayers(j);
						} else {
							entities.remove(i);
							reOrganizePlayers(i);
						}
						break;
					}
				}
			}
		}
	}
	
	public int getMainPlayer() {
		return mainPlayer;
	}
	
	public void changeMainPlayer(int n) { //se n > 0 allora il player incrementa, se n < 0 allora decrementa
		if(!canChange) return;
		if(n > 0) {
			if(mainPlayer < entities.size() - 1) {
				mainPlayer++;
			}
			else if(mainPlayer == entities.size() - 1){
				mainPlayer = 0;
			}
			waitingForChange = 0;
			canChange = false;
		} else if (n < 0) {
			if(mainPlayer > 0) {
				mainPlayer--;
			}
			else if(mainPlayer == 0){
				mainPlayer = entities.size() - 1;
			}
			waitingForChange = 0;
			canChange = false;
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

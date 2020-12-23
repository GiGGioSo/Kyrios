package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import input.KeyHandler;
import input.MouseHandler;
import states.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {

	// Dimension
	public static final int WIDTH = 960;
	public static final int HEIGHT = 720;

	// Game Thread and Game Loop
	private Thread thread;
	private boolean running;

	//GameLoop variables
	final double GAME_HERTZ = 60.0;
	final double TBU = 1000000000 / GAME_HERTZ; // Time before Update
	final int MUBR = 5; // Most updates before Render
	final double TARGET_FPS = 60.0;
	final double TTBR = 1000000000 / TARGET_FPS; // Total time before render

	double lastUpdateTime;
	double lastRenderTime;
	int frameCount;
	int lastSecondTime;
	int oldFrameCount;
	
	public static int FRAMES = 0;

	// Image
	private BufferedImage image;
	private Graphics2D g;
	
	//Input handlers
	private MouseHandler mouse;
	private KeyHandler key;
	
	//GameStateManager
	private GameStateManager gsm;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this, "GameThread");
			thread.start();
		}
	}

	private void init() {
		running = true;
		
		key = new KeyHandler(this);
		mouse = new MouseHandler(this);
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		gsm = new GameStateManager();
	}

	@Override
	public void run() {
		init();
		
		lastUpdateTime = System.nanoTime();
		lastSecondTime = (int) (lastUpdateTime / 1000000000);
		
		while(running) {
			
			double now = System.nanoTime();
			int updateCount = 0;
			
			while(((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
				
				update();
				input(mouse, key);
				
				lastUpdateTime += TBU;
				updateCount++;
			}
			if(now - lastUpdateTime > TBU) {
				lastUpdateTime = now - TBU;
			}
			input(mouse, key);
			draw();
			drawToScreen();
			lastRenderTime = now;
			frameCount++;
			FRAMES++;
			if(FRAMES > 100) FRAMES = 0;
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if(thisSecond > lastSecondTime) {
				if(frameCount != oldFrameCount) {
					//System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
					oldFrameCount = frameCount;
				}
				System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
				frameCount = 0;
				lastSecondTime = thisSecond;
			}
			while(now - lastRenderTime < TBU && now - lastUpdateTime < TBU) {
				Thread.yield();
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					System.out.println("ERROR : yielding thread");
				}
				now = System.nanoTime();
			}
		}
	}
	
	private void input(MouseHandler mouse, KeyHandler key) {
		gsm.input(mouse, key);
	}

	private void update() {
		key.tick();
		gsm.update();
	}

	private void draw() {
		g.setColor(new Color(100, 100, 250));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		gsm.draw(g);
		
	}

	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
}

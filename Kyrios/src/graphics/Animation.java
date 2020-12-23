package graphics;

import java.awt.image.BufferedImage;

public class Animation {
	
	private final int DEFAULT_DELAY = 10;

	private BufferedImage[] frames;
	private int currentFrame; // frame corrente ( quello da disengare sullo schermo)
	private int delay; // delay tra un frame e l'altro
	private int counter; // servirà per sapere quando cambiare frame
	private int numFrames; // numero di frame nell'array 'frames'
	private int timesPlayed; //numero di volte che è stata eseguita l'animazione
	
	public Animation(BufferedImage[] frames, int delay) {
		initializeFrames(frames);
		setDelay(delay);
	}
	
	public Animation(BufferedImage[] frames) {
		initializeFrames(frames);
		setDelay(DEFAULT_DELAY);
	}
	
	private void initializeFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		numFrames = frames.length;
		counter = 0;
		timesPlayed = 0;
	}
	
	public void setDelay(int delay) { this.delay = delay; }
	public void setCurrentFrame(int currentFrame) { this.currentFrame = currentFrame; }
	
	public void update() {
		if(delay == -1) {
			currentFrame = 0;
		} else {
			counter++;
			
			if(counter == delay) {
				currentFrame++;
				counter = 0;
			}
			
			if(currentFrame == numFrames) {
				currentFrame = 0;
				timesPlayed++;
			}
		}
	}
	
	public int getDelay() { return delay; }
	public int getFrameCounter() { return currentFrame; }
	public BufferedImage getFrame() { return frames[currentFrame]; }
	public BufferedImage[] getAllFrames() { return frames; }
	public boolean hasPlayedOnce() { return timesPlayed > 	 0; }
	
	public void resetAnimation() {
		currentFrame = 0;
		timesPlayed = 0;
		counter = 0;
	}
	
}




















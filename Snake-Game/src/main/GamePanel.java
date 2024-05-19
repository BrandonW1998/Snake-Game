package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import util.KeyHandler;

public class GamePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;

	// SCREEN SETTINGS
	private final int tileSize = 32;
	private final int maxScreenCols = 20;
	private final int maxScreenRows = 20;
	private final int screenWidth = maxScreenCols * tileSize;
	private final int screenHeight = maxScreenRows * tileSize;

	// FRAMES PER SECOND
	private final int fps = 60;

	// SYSTEMS / UTILITIES
	private final KeyHandler keyH = new KeyHandler();

	// THREADS
	private Thread gameThread;

	// GAME STATE
	private final int titleState = 0;
	private final int playState = 1;
	private final int resultState = 2;
	private int gameState = 0;

	// ASSETS / HOLDERS
	private Title title = new Title(this, keyH);
	private Player player = new Player(this, keyH);
	private Result result = new Result(this, keyH);

	// GamePanel Constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	// Setup game variables
	public void setupGame() {

	}

	// Start game thread
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	// Run game (at given frame-rate)
	@Override
	public void run() {
		double frameRate = 1000000000 / fps; // Time of one frame (1/60th of a second)
		double delta = 0; // Difference in previous and current time
		long preTime = System.nanoTime(); // Previous time
		long curTime; // Current time

		// While game thread is running
		while (gameThread != null) {
			// Get current time
			curTime = System.nanoTime();
			// Track difference from previous to current time
			delta += (curTime - preTime) / frameRate;
			// Make current time the previous time
			preTime = curTime;

			// If time difference greater than one frame
			// update(), repaint()
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	// Update variables
	public void update() {
		switch (gameState) {
		case titleState:
			title.update();
			break;
		case playState:
			player.update();
			break;
		case resultState:
			result.update();
			break;
		}
	}

	// Draw frame
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D frame = (Graphics2D) g;

		switch (gameState) {
		case titleState:
			title.draw(frame);
			break;
		case playState:
			player.draw(frame);
			break;
		case resultState:
			result.draw(frame);
			break;
		}
	}

}

package main;

import java.awt.Graphics2D;

import util.KeyHandler;

public class Title {

	// SYSTEMS / TOOLS
	private final GamePanel gp;
	private final KeyHandler keyH;

	// Title variables
	private int option; // Which option player chooses

	// Title Constructor
	public Title(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		startNewTitle();
	}

	// Resets title variables
	public void startNewTitle() {
		option = 0;
	}

	// Update title variables
	public void update() {

	}

	// Paint title screen
	public void draw(Graphics2D frame) {

	}
}

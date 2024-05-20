package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import util.KeyHandler;

public class Title {

	// SYSTEMS / TOOLS
	private final GamePanel gp;
	private final KeyHandler keyH;

	// Button variables
	private final int buttonWidth;
	private final int buttonHeight;
	private final int buttonX;
	private int option; // Which option player is hovering

	// Fonts
	private final Font arial_60; // Arial, PLAIN, 80
	private final Font arial_10; // Arial, PLAIN, 10

	// Start Button
	private final String startString;
	private final Rectangle startButton;
	private final int startY;

	// Control Button
	private final String controlString;
	private final Rectangle controlButton;
	private final int controlY;

	// Exit Button
	private final String exitString;
	private final Rectangle exitButton;
	private final int exitY;

	// Interact interval variables
	private final int interval;
	private int count;

	// Title Constructor
	public Title(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		startString = "Start Game";
		controlString = "Controls";
		exitString = "Exit Game";

		buttonX = gp.getScreenWidth() / 5;
		startY = gp.getScreenHeight() / 7;
		controlY = gp.getScreenHeight() / 7 * 3;
		exitY = gp.getScreenHeight() / 7 * 5;
		buttonWidth = gp.getScreenWidth() - (buttonX * 2);
		buttonHeight = gp.getScreenHeight() / 7;
		startButton = new Rectangle(buttonX, startY, buttonWidth, buttonHeight);
		controlButton = new Rectangle(buttonX, controlY, buttonWidth, buttonHeight);
		exitButton = new Rectangle(buttonX, exitY, buttonWidth, buttonHeight);

		interval = 10;

		arial_10 = new Font("Arial", Font.BOLD, 20);
		arial_60 = new Font("Arial", Font.BOLD, 60);

		startNewTitle();
	}

	// Resets title variables
	public void startNewTitle() {
		option = 0;
		count = 0;
	}

	public void moveUpOption() {
		if (option != 0) {
			option--;
		}
	}

	public void moveDownOption() {
		if (option != 2) {
			option++;
		}
	}

	// Update title variables
	public void update() {
		if (count < interval)
			count++;
		if (keyH.isUp() || keyH.isDown()) {
			if (keyH.isUp() && count == interval) {
				count = 0;
				moveUpOption();
			}
			if (keyH.isDown() && count == interval) {
				count = 0;
				moveDownOption();
			}
		}

		if (keyH.isInteract()) {
			switch (option) {
			case 0:
				gp.setGameState(gp.getPlayState());
				break;
			case 1:
				// TODO Set gameState to controlState
				break;
			case 2:
				gp.endGame();
				break;
			}
		}
	}

	// Paint title screen
	public void draw(Graphics2D frame) {
		frame.setFont(arial_10);
		frame.setColor(Color.white);
		frame.fill(startButton);
		frame.drawString(startString, startButton.x, startButton.y);
		frame.fill(controlButton);
		frame.drawString(controlString, controlButton.x, controlButton.y);
		frame.fill(exitButton);
		frame.drawString(exitString, exitButton.x, exitButton.y);
		frame.setFont(arial_60);
		frame.setColor(Color.black);
		switch (option) {
		case 0:
			frame.drawString(startString, startButton.x, startButton.y + buttonHeight);
			break;
		case 1:
			frame.drawString(controlString, controlButton.x, controlButton.y + buttonHeight);
			break;
		case 2:
			frame.drawString(exitString, exitButton.x, exitButton.y + buttonHeight);
			break;
		}
	}
}

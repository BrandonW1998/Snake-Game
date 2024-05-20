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
	private final Font arial_80; // Arial, PLAIN, 80

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

		buttonX = 100;
		startY = 100;
		controlY = 280;
		exitY = 460;
		buttonWidth = 440;
		buttonHeight = 80;
		startButton = new Rectangle(buttonX, startY, buttonWidth, buttonHeight);
		controlButton = new Rectangle(buttonX, controlY, buttonWidth, buttonHeight);
		exitButton = new Rectangle(buttonX, exitY, buttonWidth, buttonHeight);

		interval = 10;

		arial_80 = new Font("Default", Font.PLAIN, 80);

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
		frame.fill(startButton);
		frame.drawString(startString, startButton.x, startButton.y);
		frame.fill(controlButton);
		frame.drawString(controlString, controlButton.x, controlButton.y);
		frame.fill(exitButton);
		frame.drawString(exitString, exitButton.x, exitButton.y);
		switch (option) {
		case 0:
			frame.draw(startButton);
			frame.setFont(arial_80);
			frame.setColor(Color.white);
			frame.drawString(startString, startButton.x, startButton.y + 80);
			break;
		case 1:
			frame.draw(controlButton);
			frame.setFont(arial_80);
			frame.setColor(Color.white);
			frame.drawString(controlString, controlButton.x, controlButton.y + 80);
			break;
		case 2:
			frame.draw(exitButton);
			frame.setFont(arial_80);
			frame.setColor(Color.white);
			frame.drawString(exitString, exitButton.x, exitButton.y + 80);
			break;
		}
	}
}

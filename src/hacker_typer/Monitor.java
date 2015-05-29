package hacker_typer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import swing_utils.loc;

public class Monitor extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8791554566652975440L;
	private Image PowerMonitor,PowerComputer, computer, cdFlasher, floppyFlasher, backgroundImage;
	private boolean isOn;
	private int buttonPosX, buttonPosY, scale;
	private loc cdBlinkOne, cdBlinkTwo, floppyBlink, hardBlink, powerButton;
	private Random randGen;
	private int lastTime;
	private Font monitorFont;
	private Cookie cookie;
	Thread thread;

	public Monitor(String computer, String letters, String powerMonitor, String powerComputer,
			String cdFlasher, String floppyFlasher, int scale, Color bGColor)
			throws IOException {

		// Read in backgroundImage
		backgroundImage = ImageIO.read(new File(computer));

		// set bGColor
		this.setBackground(bGColor);

		// Initialize scale
		this.scale = scale;

		// Read in the button image
		this.PowerMonitor = ImageIO.read(new File(powerMonitor));
		this.PowerComputer = ImageIO.read(new File(powerComputer));

		// Read in the computer image
		this.computer = ImageIO.read(new File(computer));

		// Read in the cd flasher image
		this.cdFlasher = ImageIO.read(new File(cdFlasher));
		System.out.println("Width: " + this.cdFlasher.getWidth(this));
		System.out.println("Height: " + this.cdFlasher.getHeight(this));

		// Read in the floppy flasher image
		this.floppyFlasher = ImageIO.read(new File(floppyFlasher));

		// "Computer" initial state is off
		isOn = false;

		// Set button's position in the panel
		buttonPosX = 0;
		buttonPosY = 0;

		// Initialize random number generator
		randGen = new Random();

		// Initialize last seconds holder
		lastTime = 0;

		// Set location for the blinkers located in the background
		cdBlinkOne = new loc(239, 65);
		cdBlinkTwo = new loc(239, 131);
		floppyBlink = new loc(82, 244);
		hardBlink = new loc(168, 310);
		powerButton = new loc(932, 448);

		// set preferred size
		this.setPreferredSize(getDimension());

		initialize();

		System.out.println();
		System.out.println("X: " + cdBlinkOne.getX());
		System.out.println("Y: " + cdBlinkOne.getY());

		thread = new Thread(this);
		thread.start();
	}

	/*
	 * private void generateChar() { int scaleNum =
	 * (backgroundImage.getHeight(getParent()) / scale);
	 * System.out.print(scaleNum); }
	 */

	private void initialize() {
		
	}

	private int getSeconds() {
		return (int) ((int) System.currentTimeMillis() * Math.pow(10, -3));
	}

	public Dimension getDimension() {
		return new Dimension(backgroundImage.getWidth(this),
				backgroundImage.getHeight(this));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background image.
		g.drawImage(getImage(), 0, 0, this);

		// TODO make it so the lights toggle on and off rather than flicker

		// Cd drive 1
		if (randGen.nextInt(10) < 2) {
			g.drawImage(cdFlasher, cdBlinkOne.getX(), cdBlinkOne.getY(), this);
		}

		// Cd drive 2
		if (randGen.nextInt(10) < 2) {
			g.drawImage(cdFlasher, cdBlinkTwo.getX(), cdBlinkTwo.getY(), this);
		}

		// Floppy drive
		if (randGen.nextInt(10) < 2) {
			g.drawImage(floppyFlasher, floppyBlink.getX(), floppyBlink.getY(),
					this);
		}

		// Hard drive
		if (randGen.nextInt(10) < 4) {
			g.drawImage(floppyFlasher, hardBlink.getX(), hardBlink.getY(), this);
			g.drawImage(floppyFlasher, hardBlink.getX(), hardBlink.getY() + 1,
					this);
		}
		lastTime = getSeconds();

		// turn on power
		if (isOn) {
			g.drawImage(PowerMonitor, powerButton.getX(), powerButton.getY(), this);
		}
		
		// turn on computer power
			g.drawImage(PowerComputer, 240, 239, this);
	}

	private Image getImage() {

		Image foo = this.createImage(WIDTH, HEIGHT);
		// TODO Auto-generated method stub
		return backgroundImage;
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			// Delay
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {

			}

		}
	}

	public void eventInput(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Monitor");
		/*
		 * if (e.getX() > 931 && e.getX() < 968 && e.getY() > 457 && e.getY() <
		 * 494) { System.out.println("toggle"); isOn = !isOn; }
		 */

	}

	public void mouseClick(MouseEvent e) {
		if (e.getX() > 929 && e.getX() < 976 && e.getY() > 472
				&& e.getY() < 519) {
			System.out.println("toggle");
			isOn = !isOn;
		}
	}

	public int getLinesPerSec() {
		return 5;
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

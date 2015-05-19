package hacker_typer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.sun.xml.internal.bind.v2.runtime.Location;

import swing_utils.JPanelWithBackground;
import swing_utils.loc;

public class Monitor extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8791554566652975440L;
	private Image button, computer, cdFlasher, floppyFlasher, backgroundImage;
	private boolean isOn;
	private int buttonPosX, buttonPosY, scale;
	private Dimension computerimageSize;
	private loc cdBlinkOne, cdBlinkTwo, floppyBlink, hardBlink;
	private Random randGen;
	private int lastTime;
	private Image[] eightBitLetters;
	private String letterArrangement;
	Thread thread;

	public Monitor(String computer, String letters, String button,
			String cdFlasher, String floppyFlasher, int scale)
			throws IOException {

		backgroundImage = ImageIO.read(new File(computer));
		backgroundImage = backgroundImage.getScaledInstance(
				scale,
				backgroundImage.getHeight(this) * scale
						/ backgroundImage.getWidth(this), 0);
		this.scale = scale;
		// Create eightBitLetters array
		eightBitLetters = splitImage(ImageIO.read(new File(letters)));

		// Read in the button image
		this.button = ImageIO.read(new File(button));

		// Read in the computer image
		this.computer = ImageIO.read(new File(computer));
		// Save image dimensions
		computerimageSize = new Dimension(this.computer.getWidth(this),
				this.computer.getHeight(this));

		// Read in the cd flasher image
		this.cdFlasher = ImageIO.read(new File(cdFlasher));
		this.cdFlasher = this.cdFlasher.getScaledInstance(16, 5, 0);
		System.out.println("Width: " + this.cdFlasher.getWidth(this));
		System.out.println("Height: " + this.cdFlasher.getHeight(this));

		// Read in the floppy flasher image
		this.floppyFlasher = ImageIO.read(new File(floppyFlasher));
		this.floppyFlasher = this.floppyFlasher.getScaledInstance(5, 5, 0);

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
		cdBlinkOne = new loc(239, 76);
		cdBlinkTwo = new loc(239, 142);
		floppyBlink = new loc(82, 254);
		hardBlink = new loc(168, 320);

		System.out.println();
		System.out.println("X: " + cdBlinkOne.getX());
		System.out.println("Y: " + cdBlinkOne.getY());

		thread = new Thread(this);
		thread.start();
	}

	
	//TODO make this work
	private Image[] splitImage(Image input) {
		//Image retX = new Image();
				
		//		new Image[(input.getHeight(this)/3)*(input.getWidth(this)/3)]();
		BufferedImage foo = (BufferedImage) input;
		for (int i = 0; i < input.getHeight(this) - 3; i += 3) {
			for (int q = 0; q < input.getWidth(this) - 3; q += 3) {
		//		retX.add(foo.getSubimage(q, i, 3, 3));
			}
		}
		//Image[] bar = retX.toArray();
		return null;

	}

	private void generateChar() {
		int scaleNum = (backgroundImage.getHeight(getParent()) / scale);
		System.out.print(scaleNum);
	}

	private void togglePower() {
		// TODO make this work
	}

	private int getSeconds(long milli) {
		return (int) ((int) milli * Math.pow(10, -3));
	}

	public Dimension getDimension() {
		return new Dimension(backgroundImage.getWidth(this),
				backgroundImage.getHeight(this));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background image.
		g.drawImage(backgroundImage, 0, 10, this);

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
		lastTime = getSeconds(System.currentTimeMillis());
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

}

package hacker_typer_netbeans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

import utils.CreateArrayFromFile;
import utils.loc;

// It works, trust me
public class Monitor extends JPanel implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8791554566652975440L;
	private Image PowerMonitor, PowerComputer, cdFlasher, floppyFlasher,
			backgroundImage;
	private boolean isOn;
	private loc cdBlinkOne, cdBlinkTwo, floppyBlink, hardBlink, powerButton;
	private Random randGen;
	private Font monitorFont;
	public Cookie cookie;
	private JTextArea screen;
	private String[] code;
	private ArrayList<String> changingCode;
	Thread thread;
	static final String newline = System.getProperty("line.separator");

	/**
	 * Dear future self, when writing this constructor you thought for some
	 * reason that you were going to make this class so it could different uses.
	 * creating possibly the most requirements for a constructor that you will
	 * ever code. Congratz
	 * 
	 * @param computer
	 * @param letters
	 * @param powerMonitor
	 * @param powerComputer
	 * @param cdFlasher
	 * @param floppyFlasher
	 * @param scale
	 * @param bGColor
	 * @param code
	 * @throws IOException
	 * @throws FontFormatException
	 */
	public Monitor(String computer, String letters, String powerMonitor,
			String powerComputer, String cdFlasher, String floppyFlasher,
			int scale, Color bGColor, String code) throws IOException,
			FontFormatException {
		CreateArrayFromFile reader = new CreateArrayFromFile(code);
		this.code = reader.getArray();
		changingCode = new ArrayList<String>();
		changingCode = genArrayList(this.code);
		{ // image stuff
			// Read in backgroundImage
			backgroundImage = ImageIO.read(new File(computer));

			// Read in the button image
			this.PowerMonitor = ImageIO.read(new File(powerMonitor));
			this.PowerComputer = ImageIO.read(new File(powerComputer));

			ImageIO.read(new File(computer));

			// Read in the cd flasher image
			this.cdFlasher = ImageIO.read(new File(cdFlasher));

			// Read in the floppy flasher image
			this.floppyFlasher = ImageIO.read(new File(floppyFlasher));

			// Set location for the blinkers located in the background
			cdBlinkOne = new loc(239, 65);
			cdBlinkTwo = new loc(239, 131);
			floppyBlink = new loc(82, 244);
			hardBlink = new loc(168, 310);
			powerButton = new loc(932, 448);
		}

		// "Computer" initial state is off
		isOn = false;

		// set bGColor
		this.setBackground(bGColor);

		// Initialize random number generator
		randGen = new Random();

		// set preferred size
		this.setPreferredSize(getDimension());

		// read in font
		monitorFont = Font.createFont(Font.TRUETYPE_FONT, new File(letters))
				.deriveFont(Font.BOLD, 12f);

		cookie = new Cookie();

		initialize();

		thread = new Thread(this);
		thread.start();
	}

	private ArrayList<String> genArrayList(String[] code2) {
		ArrayList<String> retX = new ArrayList<String>();
		for (String i : code2) {
			retX.add(i);
		}
		return retX;
	}

	/*
	 * This method initializes the child of this panel. since I want to position
	 * the child of this panel based on x and y coords, the use of the layout
	 * manager becomes more of a nuisance than a blessing, so it is set to null
	 * and the child is positioned by calling it's setBounds() method.
	 * 
	 * also the main method was getting to long for my liking
	 */
	private void initialize() {
		// set the layout manager to null
		this.setLayout(null);

		// initialize the text area
		screen = new JTextArea();
		screen.addKeyListener(this);
		// set size and position of the child
		screen.setBounds(464, 71, 478, 341);
		// set Background Color
		screen.setBackground(new Color(46, 46, 46));
		// set font style and color
		screen.setForeground(new Color(104, 246, 0));
		screen.setFont(monitorFont.deriveFont(monitorFont.getSize() * 72));
		// set wrapping to true
		screen.setLineWrap(true);
		screen.setAutoscrolls(true);
		screen.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "none");
		screen.setEditable(false);
		JScrollPane scrollComponent = new JScrollPane(screen);
		scrollComponent.setEnabled(true);
		scrollComponent.setVisible(true);
		scrollComponent.setBounds(464, 71, 478, 341);
		scrollComponent
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollComponent.setBorder(null);
		// finally we add it to the parent
		this.add(scrollComponent, 0);

		// just to refresh the panel (though its being refreshed every 50mil for
		// the blinkers)
		this.repaint();
	}

	// I am not sure if we need this, but too scared to delete.
	private int getSeconds() {
		return (int) ((int) System.currentTimeMillis() * Math.pow(10, -3));
	}

	// not sure why this guy exists, probably for a decent reason
	public Dimension getDimension() {
		return new Dimension(backgroundImage.getWidth(this),
				backgroundImage.getHeight(this));
	}

	/** this is one well commented method **/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background image.
		g.drawImage(getImage(), 0, 0, this);

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
		getSeconds();

		// turn on power
		if (isOn) {
			g.drawImage(PowerMonitor, powerButton.getX(), powerButton.getY(),
					this);
			// turns the screen on
			this.getComponent(0).setVisible(true);
		} else {
			//
			this.getComponent(0).setVisible(false);
		}

		// turn on computer power, never turns off (why isn't this part of the
		// backgroundImage?)
		g.drawImage(PowerComputer, 240, 239, this);
	}

	// ??? Okay this is stupid
	private Image getImage() {

		Image foo = this.createImage(WIDTH, HEIGHT);
		// TODO Auto-generated method stub
		return backgroundImage;
	}

	@Override
	public void run() /* Forest, run! */{
		while (true) {
			repaint();
			// Delay
			try {
				Thread.sleep(50);

				// TODO make this work
				// screen.setCaretPosition(screen.getSelectionEnd());
			} catch (InterruptedException ex) {

			}

		}
	}

	public void eventInput(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseClick(MouseEvent e) {
		if (e.getX() > 929 && e.getX() < 976 && e.getY() > 472
				&& e.getY() < 519) {
			isOn = !isOn;
		}
	}

	public double getLinesPerSec() {
		return cookie.getCookiesPerSec(); // TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO make the screen clear when the code is finished
		if (isOn) {
			char c = getNextCharacter();
			if (c == '\n') {
				screen.append(newline);
				cookie.newLine();
			} else if (c == '}') {
				screen.append(Character.toString(')'));
			} else if (c == '{') {
				screen.append(Character.toString('('));
			} else if (c == '[') {
				screen.append(Character.toString('('));
			} else if (c == ']') {
				screen.append(Character.toString(')'));

			} else {
				screen.append(Character.toString(c));
			}
		}
	}

	/**
	 * where all the screen magic happens
	 **/
	private char getNextCharacter() {
		System.out.println("size:" + changingCode.size());
		if (changingCode.size() == 0) {
			changingCode = genArrayList(code);
		}
		if (changingCode.get(0).length() < 1) {
			changingCode.remove(0);
			return '\n';
		}
		char retX = changingCode.get(0).charAt(0);
		changingCode.set(0, changingCode.get(0).substring(1));
		return retX;
	}

	public int getAmountOfLines() {
		return cookie.getNumberOfCookies();
	}
}

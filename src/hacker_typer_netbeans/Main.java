package hacker_typer_netbeans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.security.auth.Refreshable;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * It's dangerous to go alone! Take this. 
 * 		 /| ________________ 
 * O|===|* >________________> 
 * 		 \|
 *
 * 
 * @author ecford
 *
 */
public class Main extends JPanel implements MouseListener, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 561173046829833968L;
	private ImageIcon monitorIcon, settingsIcon, shopIcon;
	private Monitor panel1;

	public Main() throws IOException, FontFormatException {
		super(new BorderLayout());
		initialize();
		Thread thread = new Thread(this);
		thread.start();

	}

	private void initialize() throws IOException, FontFormatException {


		Monitor panel1 = new Monitor("images/computer.png", "font/3-bit.ttf",
				"images/power.png", "images/powerComputer.png",
				"images/cdFlasher.png", "images/floppyFlasher.png", 1024,
				Color.WHITE, "code/Monitor"); // same text as the
															// Monitor class.
															// just in txt form
		panel1.setBounds(0, 0, 1024, 1024);

		// Add the tabbed pane to this panel
		add(panel1, BorderLayout.NORTH);
		panel1.addMouseListener(this);
	}

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 * 
	 * @throws IOException
	 */
	protected static ImageIcon createImageIcon(String path) throws IOException {
		File img = new File(path);
		return new ImageIcon(ImageIO.read(img).getScaledInstance(20, -1, 0));
	}

	public void eventOutput(String eventDescription, MouseEvent e) {
		panel1.eventInput(e);

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		eventOutput("Mouse pressed (# of clicks: " + e.getClickCount() + ")", e);
	}

	public void mouseReleased(MouseEvent e) {
		eventOutput("Mouse released (# of clicks: " + e.getClickCount() + ")",
				e);
	}

	public void mouseEntered(MouseEvent e) {
		eventOutput("Mouse entered", e);
	}

	public void mouseExited(MouseEvent e) {
		eventOutput("Mouse exited", e);
	}

	public void mouseClicked(MouseEvent e) {
		eventOutput("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);
		eventOutput("Mouse click location (" + e.getX() + "," + e.getY() + ")",
				e);
		tabbedPane.mouseClick(e);

	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() /* Forest, run! */{
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				try {
					createAndShowGUI();
				} catch (IOException | FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 * 
	 * @throws IOException
	 * @throws FontFormatException
	 */
	private static void createAndShowGUI() throws IOException,
			FontFormatException {
		// Create and set up the window.
		JFrame frame = new JFrame("Hacker Typer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add content to the window.
		frame.add(new Main(), BorderLayout.CENTER);

		// Display the window.
		frame.setFocusable(true);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void run() /* Forest, run! */{
		while (true) {
			refresh();
			try {
				Thread.sleep(50); // night night!
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void refresh() {
		bottomBar.setText("Lines: " + getAmountOfLines() + " | LinesPerSec: "
				+ getLinesPerSec() + "LPS");

	}

}

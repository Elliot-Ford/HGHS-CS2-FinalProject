package hacker_typer;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.*;

import swing_practice.CardLayoutDemo;
import swing_utils.JPanelWithBackground;

public class Interface implements ItemListener, MouseListener {
	JPanel cards; // a panel that uses CardLayout
	final static String COMPUTERPANEL = "Monitor";
	final String SETTINGSPANEL = "Settings";
	private Image backgroundImage;

	private void addComponentToPane(Container pane) throws IOException {
		// setting up panel for panes
		JPanel comboBoxPane = new JPanel();
		String comboBoxItems[] = { COMPUTERPANEL, SETTINGSPANEL };
		JComboBox cb = new JComboBox(comboBoxItems);
		cb.setEditable(false);
		cb.addItemListener(this);
		comboBoxPane.add(cb);

		// First card
		Monitor card1 = new Monitor("computer.png", "letters.png", "power.png",
				"cdFlasher.png", "floppyFlasher.png", 1024);
		card1.setBackground(new Color(153, 153, 255, 255));
		card1.setPreferredSize(new Dimension(card1.getDimension()));
		// Second card
		JPanel card2 = new JPanel();
		card2.add(new JTextField("TextField", 20));

		// Card holder panel
		cards = new JPanel(new CardLayout());
		cards.add(card1, COMPUTERPANEL);
		cards.add(card2, SETTINGSPANEL);

		pane.add(comboBoxPane, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);

	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, (String) evt.getItem());
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 * 
	 * @throws IOException
	 */
	private static void createAndShowGUI() throws IOException {
		// Create and set up the window.
		JFrame frame = new JFrame("HACKER TYPER");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		Interface demo = new Interface();
		demo.addComponentToPane(frame.getContentPane());

		// Display the window

		frame.setSize(new Dimension(1028, 512));
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					createAndShowGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("click!");
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

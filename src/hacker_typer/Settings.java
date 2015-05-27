package hacker_typer;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class Settings extends JPanel implements Runnable {

	private Thread thread;

	public Settings(Color bgColor) {
		this.setBackground(bgColor);
		thread = new Thread(this);
		thread.start();
	}

	public void createAndShowGUI() {
		JColorChooser colorChooser = new JColorChooser(this.getBackground());
		colorChooser.setOpaque(true);
		colorChooser.setPreferredSize(new Dimension(600, 300));
		this.add(colorChooser);
	}

	@Override
	public void run() {
		createAndShowGUI();

	}
}

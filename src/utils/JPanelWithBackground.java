package utils;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel {
	protected Image backgroundImage;
	protected int scale;

	// Some code to initialize the background image.
	// Here, we use the constructor to load the image. This
	// can vary depending on the use case of the panel.
	public JPanelWithBackground(String fileName, int scale) throws IOException {
		backgroundImage = ImageIO.read(new File(fileName));
		backgroundImage = backgroundImage.getScaledInstance(
				scale,
				backgroundImage.getHeight(this) * scale
						/ backgroundImage.getWidth(this), 0);
		this.scale = scale;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the background image.
		g.drawImage(backgroundImage, 0, 0, this);
	}

	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame();
		f.getContentPane().add(new JPanelWithBackground("computer.png", 512));
		f.setVisible(true);
	}
}

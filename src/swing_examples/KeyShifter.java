package swing_examples;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class KeyShifter {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new KeyShifter().makeUI();
			}
		});
	}

	void makeUI() {

		JTextArea textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {

				char c = (char) (ke.getKeyChar() + 1);
				ke.setKeyChar(c);
			}
		});

		JFrame frame = new JFrame("Key Shifter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.add(textArea);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
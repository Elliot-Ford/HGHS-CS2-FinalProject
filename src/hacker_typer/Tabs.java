package hacker_typer;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;

public class Tabs extends JTabbedPane {
	private Color bGColor;

	public Tabs(Color bGColor) {
		super();
		this.bGColor = bGColor;
	}

	public Color getBGColor() {
		return bGColor;
	}

	public void setBGColor(Color bGColor) {
		this.bGColor = bGColor;
	}

	public void eventInput(MouseEvent e) {
		System.out.println(this.getSelectedIndex());
		System.out.println(this.getComponentAt(this.getSelectedIndex()));
		switch (this.getSelectedIndex()) {
		case 0:
			((Monitor) this.getComponentAt(0)).eventInput(e);
			break;

		default:
			break;
		}

	}

	public void mouseClick(MouseEvent e) {
		switch (this.getSelectedIndex()) {
		case 0:
			((Monitor) this.getComponentAt(0)).mouseClick(e);
			break;

		default:
			break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		switch (this.getSelectedIndex()) {
		case 0:
			((Monitor) this.getComponentAt(0)).keyPressed(e);
			break;

		default:
			break;
		}
	}

	public int getLinesPerSec() {
		return ((Monitor) this.getComponent(0)).getLinesPerSec();
		
	}

}

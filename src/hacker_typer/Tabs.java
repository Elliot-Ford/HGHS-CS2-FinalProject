package hacker_typer;

import java.awt.Color;
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
	
}


}

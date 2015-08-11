package awt_practice;
import java.awt.*;
public class Example3 extends java.applet.Applet {

	public void init() {
		add(new Button("One"));
		add(new Button("Two"));
	}
	
	public Dimension preferredSize() {
		return new Dimension(200,100);
	}
	
	public static void main(String[] args) {
		Frame f = new Frame("Example 3");
		Example3 ex = new Example3();
		
		ex.init();
		f.add("Center",ex);
		
		f.pack();
		f.show();
	}
}

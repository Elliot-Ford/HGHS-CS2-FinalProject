package awt_practice;

import java.awt.*;

@SuppressWarnings("serial")
public class Example1b extends java.applet.Applet {
	
	public static void main(String[] args) {
		Frame f = new Frame("Example 1b");
		
		Example1b ex = new Example1b();
		f.add("Center", ex);
		
		f.pack();
		f.show();
	}

}

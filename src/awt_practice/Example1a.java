package awt_practice;
import java.awt.*;
public class Example1a extends Panel{
	public static void main(String [] args) {
		Frame f = new Frame("Example 1a");
		Example1a ex = new Example1a();
		f.add("Center", ex);
		
		f.pack();
		f.show();
	}
}

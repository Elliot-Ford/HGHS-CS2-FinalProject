package threadtests;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.print.attribute.AttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

public class Test {

	String webUrl;
	URL url;
	URLConnection connection;
	InputStream is;
	InputStreamReader isr;
	BufferedReader br;

	public Test() throws Exception {
		webUrl = "http://www.hdwallpapers.in/";
		url = new URL(webUrl);
		connection = url.openConnection();
		is = connection.getInputStream();
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		HTMLEditorKit htmlKit = new HTMLEditorKit();
		HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
		HTMLEditorKit.Parser parser = new ParserDelegator();
		HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
		parser.parse(br, callback, true);
		AttributeSet attributes = iterator.getAttributes();
        String imgSrc = (String) attributes.getAttribute(HTML.Attribute.SRC);
        if (!(imgSrc.startsWith("http"))) 
        {
            url = url + imgSrc;
        } 
        else 
        {
            url = imgSrc;
         }
	}

	public static void main(String[] args) throws Exception {
		Test foo = new Test();
	}

}

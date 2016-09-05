package text;

import java.awt.Font;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class CenterableText {

	float x;
	float y;
	int fontSize;
	String words;
	TrueTypeFont font;
	
	public CenterableText(float y, String words, int fontSize) {
		this.y = y;
		this.words = words;
		this.fontSize = fontSize;
	}
	
	public void setUp() {
		Font awtFont = new Font("Sans-serif", Font.BOLD, fontSize); //name, style (PLAIN, BOLD, or ITALIC), size
		font = new TrueTypeFont(awtFont, false); //base Font, anti-aliasing true/false
		x = (Display.getWidth()/2) - (font.getWidth(words)/2);
	}
	
	public void destroy() {}
	
	public void draw(Color color) {
		font.drawString(x, y, words, color); //x, y, string to draw, color
	}
	
}

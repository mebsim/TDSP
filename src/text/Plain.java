package text;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class Plain {
	
	float x;
	float y;
	int fontSize;
	String words;
	
	public Plain(float x, float y, String words, int fontSize) {
		this.x = x;
		this.y = y;
		this.words = words;
		this.fontSize = fontSize;
	}
	
	public void setUp() {}
	
	public void destroy() {}
	
	public void draw(Color color) {
		TrueTypeFont font;
		Font awtFont = new Font("Sans-serif", Font.BOLD, fontSize); //name, style (PLAIN, BOLD, or ITALIC), size
		font = new TrueTypeFont(awtFont, false); //base Font, anti-aliasing true/false
		font.drawString(x, y, words, color); //x, y, string to draw, color
	}
}

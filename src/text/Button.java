package text;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import user.M;

public class Button {

	M mouse = new M();
	int mx;
	int my;
	float x;
	float y;
	int fontSize;
	String words;
	int textWidth = 0;
	int textHeight = 0;
	TrueTypeFont font;
	
	public Button(float x, float y, String words, int fontSize) {
		this.x = x;
		this.y = y;
		this.words = words;
		this.fontSize = fontSize;
	}
	
	public void setUp() {
		Font awtFont = new Font("Sans-serif", Font.BOLD, fontSize); //name, style (PLAIN, BOLD, or ITALIC), size
		font = new TrueTypeFont(awtFont, false); //base Font, anti-aliasing true/false
	}
	
	public void destroy() {}
	
	public boolean checkIfHover() {
		boolean hover = false;
		if(mx > x && mx < x + textWidth && my > y && my < y + textHeight) {
			hover = true;
		} else {
			hover = false;
		}
		return hover;
	}
	
	public boolean checkIfClicked() {
		boolean clicked = false;
		if(mx > x && mx < x + textWidth && my > y && my < y + textHeight && mouse.isLeftButton()) {
			clicked = true;
		} else {
			clicked = false;
		}
		return clicked;
	}
	
	public void draw(Color color) {
		mx = mouse.getMx();
		my = mouse.getMy();
		textWidth = font.getWidth(words);
		textHeight = font.getHeight(words);
		System.out.println(textHeight);
		font.drawString(x, y, words, color); //x, y, string to draw, color
	}
	
}

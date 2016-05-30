package level;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import loaders.LoadTextures;
import main.WindowMaker;
import text.Plain;
import user.KB;
import user.M;

public class TitleScreen implements Levels {
	
	KB kb;
	M mouse;
	
	Plain start;
	Plain htp;
	Plain exit;
	
	int mx;
	int my;
	
	Color sc = Color.white;
	Color hc = Color.white;
	Color ec = Color.white;
	
	String state = "nothing";
	
	boolean hs;
	boolean he;
	boolean hh;
	
	public int x = 0;
	public int y = 0;
	
	Texture MapTexture;
	LoadTextures lt = new LoadTextures();
	
	WindowMaker display = new WindowMaker();

	public void getTexture() {
		MapTexture = lt.getTitleScreen();
	}

	@Override
	public void setUp() {
		getTexture();
		kb = new KB();
		mouse = new M();
		start = new Plain(325,370,"START", 24);
		htp = new Plain(280,400,"HOW TO PLAY", 24);
		exit = new Plain(340,430,"EXIT", 24);
	}

	@Override
	public void destroy() {
		unbind();
	}

	public void unbind() {
		System.out.println("Unbinding");
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		System.out.println("Unbinded texture " + MapTexture.getTextureID());
	}
	
	@Override
	public void draw() {
		state = "nothing";
		mx = mouse.getMx();
		my = mouse.getMy();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, MapTexture.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x,y);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+800,y);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+800,y+600);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x,y+600);
		GL11.glEnd();
		htp.draw(hc);
		start.draw(sc);
		exit.draw(ec);
		checkHover();
		checkClick();
	}
	
	public void checkHover() {
		if(mx > 325 && mx < 412 && my > 370 && my < 394) {
			hs = true;
			sc = Color.lightGray;
		} else {
			hs = false;
			sc = Color.white;
		}
		if(mx > 280 && mx <  460 && my > 400 && my < 424) {
			hh = true;
			hc = Color.lightGray;
		} else {
			hh = false;
			hc = Color.white;
		}
		if(mx > 340 && mx <  397 && my > 430 && my < 454) {
			he = true;
			ec = Color.lightGray;
		} else {
			he = false;
			ec = Color.white;
		}
	}
	
	public void checkClick() {
		if(hs && mouse.isLeftButton()) {
			state = "level1";
		}
		if(hh && mouse.isLeftButton()) {
			state = "htp";
		}
		if(he && mouse.isLeftButton()) {
			state = "close";
		}
	}

	@Override
	public String getState() {
		return state;
	}
	
}

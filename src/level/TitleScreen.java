package level;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import loaders.LoadTextures;
import main.WindowMaker;
import text.Button;
import user.KB;
import user.M;

public class TitleScreen implements Levels {
	
	KB kb;
	M mouse;
	
	Button start;
	Button htp;
	Button exit;
	
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
		start = new Button(0,370,"START", 24);
		htp = new Button(0,400,"HOW TO PLAY", 24);
		exit = new Button(0,430,"EXIT", 24);
		start.setUp();
		htp.setUp();
		exit.setUp();
		start.center();
		htp.center();
		exit.center();
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
		if(start.checkIfHover()) {
			sc = Color.lightGray;
		} else {
			sc = Color.white;
		}
		if(htp.checkIfHover()) {
			hc = Color.lightGray;
		} else {
			hc = Color.white;
		}
		if(exit.checkIfHover()) {
			ec = Color.lightGray;
		} else {
			ec = Color.white;
		}
	}
	
	public void checkClick() {
		if(start.checkIfClicked() && mouse.isLeftButton()) {
			state = "level1";
		}
		if(htp.checkIfClicked() && mouse.isLeftButton()) {
			state = "htp";
		}
		if(exit.checkIfClicked() && mouse.isLeftButton()) {
			state = "close";
		}
	}

	@Override
	public String getState() {
		return state;
	}
	
}

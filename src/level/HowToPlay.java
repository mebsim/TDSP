package level;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import loaders.LoadTextures;
import text.Button;
import text.CenterableText;
import user.KB;
import user.M;

public class HowToPlay implements Levels {
	
	KB kb;
	M mouse;
	
	Button goBack;
	CenterableText controls;
	CenterableText instruct;
	CenterableText pause;
	
	int mx;
	int my;
	
	Color cgb = Color.white;
	
	String state = "nothing";
	
	boolean hgb;
	
	public int x = 0;
	public int y = 0;
	
	Texture MapTexture;
	LoadTextures lt = new LoadTextures();

	public void getTexture() {
		MapTexture = lt.getHowToPlay();
	}

	@Override
	public void setUp() {
		getTexture();
		kb = new KB();
		mouse = new M();
		pause = new CenterableText(300, "ESC - PAUSE GAME", 24);
		goBack = new Button(700,550,"BACK", 24);
		controls = new CenterableText(350, "WASD -- Movement", 24);
		instruct = new CenterableText(400, "GET THE KEY, OPEN DOOR, GO THROUGH DOOR, WIN LEVEL", 24);
		pause.setUp();
		controls.setUp();
		instruct.setUp();
		goBack.setUp();
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
		goBack.draw(cgb);
		controls.draw(Color.white);
		instruct.draw(Color.white);
		pause.draw(Color.white);
		checkHover();
		checkClick();
		// System.out.println(mouse.getMx());
	}
	
	public void checkHover() {
		if(goBack.checkIfHover()) {
			cgb = Color.lightGray;
		} else {
			cgb = Color.white;
		}
	}
	
	public void checkClick() {
		if(goBack.checkIfClicked() && mouse.isLeftButton()) {
			state = "titlescreen";
		}
	}

	@Override
	public String getState() {
		return state;
	}
	
}

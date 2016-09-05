package player;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import loaders.LoadTextures;
import main.WindowMaker;
import user.KB;

public class Player {

	// players position
	int px;
	int py;
	
	//Map positions
	int mx;
	int my;
	
	// Map Size
	int MapWidth;
	int MapHeight;
	
	// Player texture for waking
	Texture texture;
	
	int hs = 5; // horizontal speed
	int vs = 5; // vertical speed
	
	// The positions of each corner of the texture
	float tx1 = 0;
	float tx2 = 0;
	float tx3 = 0.125f;
	float tx4 = 0.125f;
	float ty1 = 1;
	float ty2 = 0;
	float ty3 = 0;
	float ty4 = 1;
	
	float tx = 0;
	float ty = 0;
	
	// Collisons booleans
	boolean ncool = false;
	boolean ecool = false;
	boolean wcool = false;
	boolean scool = false;
	
	// Is the  player dead
	boolean living = true;
	
	// If the player is at the center of the screen
	boolean centeredv;
	boolean centeredh;
	
	// Counters
	float counter; // Counter for the textures
	
	// Allows access to the window maker, loading textures and keyboard
	WindowMaker Display = new WindowMaker();
	LoadTextures lt = new LoadTextures();
	KB kb = new KB();
	
	public Player(int px, int py, int MapWidth, int MapHeight) { // Sets all the players variables
		this.px = px;
		this.py = py;
		this.MapHeight = MapHeight;
		this.MapWidth = MapWidth;
	}
	
	public void setUp() {
		getTexture();
	}
	
	public void getTexture() {
		texture = lt.getPlayer();
	}
	
	public void draw() {
		if(living) {
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
			/*GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0,0);
				GL11.glVertex2f(px,py);
				GL11.glTexCoord2f(0.8f,0);
				GL11.glVertex2f(px+100,py);
				GL11.glTexCoord2f(0.8f,0.8f);
				GL11.glVertex2f(px+100,py+100);
				GL11.glTexCoord2f(0,0.8f);
				GL11.glVertex2f(px,py+100);
			GL11.glEnd();*/
			GL11.glBegin(GL11.GL_QUADS); // 0,0 x,y 1x x + widht 1y y + height // 
				GL11.glTexCoord2f(tx1,ty1);
				GL11.glVertex2f(px,py);
				GL11.glTexCoord2f(tx2,ty2);
				GL11.glVertex2f(px+100,py);
				GL11.glTexCoord2f(tx3,ty3);
				GL11.glVertex2f(px+100,py+100);
				GL11.glTexCoord2f(tx4,ty4);
				GL11.glVertex2f(px,py+100);
			GL11.glEnd();
			//System.out.println("COUNTER: " + counter);
			//System.out.println(countertu);
			movement();
			// Checks if it is centered
			if(px >= Display.getWidth()/2 - 70 && px <= Display.getWidth()/2 - 60) {
				centeredv = true;
			} else {
				centeredv = false;
			}
			if(py >= Display.getHeight()/2 - 70 && py <= Display.getHeight()/2 - 60) {
				centeredh = true;
			} else {
				centeredh = false;
			}
			if(counter > 7) {
				counter = 0;
			}
			if(kb.isAdown() && !kb.isDdown() && !kb.isWdown() && !kb.isSdown() && wcool == false) {
				tx1 = (float) (0.125f * Math.ceil(counter));
				tx2 = (float) (0.125f * Math.ceil(counter));
				tx3 = (float) (0.125f * Math.ceil(counter)) - 0.125f;
				tx4 = (float) (0.125f * Math.ceil(counter)) - 0.125f;
				ty1 = 0;
				ty2 = 1;
				ty3 = 1;
				ty4 = 0;
				counter += 0.5;
			} else if(kb.isDdown() && !kb.isAdown() && !kb.isWdown() && !kb.isSdown() && ecool == false) {
				tx1 = (float) (0.125f * Math.ceil(counter)) - 0.125f;
				tx2 = (float) (0.125f * Math.ceil(counter)) - 0.125f;
				tx3 = (float) (0.125f * Math.ceil(counter));
				tx4 = (float) (0.125f * Math.ceil(counter));
				ty1 = 1;
				ty2 = 0;
				ty3 = 0;
				ty4 = 1;
				counter += 0.5;
			} else if(kb.isWdown() && !kb.isAdown() && !kb.isDdown() && !kb.isSdown() && ncool == false) {
				tx1 = (float) (0.125f * Math.ceil(counter)) - 0.125f;
				tx2 = (float) (0.125f * Math.ceil(counter));
				tx3 = (float) (0.125f * Math.ceil(counter));
				tx4 = (float) (0.125f * Math.ceil(counter)) - 0.125f;
				ty1 = 0;
				ty2 = 0;
				ty3 = 1;
				ty4 = 1;
				counter += 0.5;
			} else if(kb.isSdown() && !kb.isAdown() && !kb.isDdown() && !kb.isWdown() && scool == false) {
				tx1 = (float) (0.125f * Math.ceil(counter));
				tx2 = (float) (0.125f * Math.ceil(counter)) - 0.125f;
				tx3 = (float) (0.125f * Math.ceil(counter)) - 0.125f;
				tx4 = (float) (0.125f * Math.ceil(counter));
				ty1 = 1;
				ty2 = 1;
				ty3 = 0;
				ty4 = 0;
				counter += 0.5;
			} else {
				counter = 0;
			}
		}
	}
	
	public void movement() {
		if(kb.isAdown() && !kb.isDdown() && !kb.isWdown() && !kb.isSdown() && wcool == false && (mx == 0 || (mx <= (Display.getWidth() - MapWidth) && px >= Display.getWidth()/2 - 64)) && px > 0) {
			px -=hs;
		} else if(kb.isDdown() && !kb.isAdown() && !kb.isWdown() && !kb.isSdown() && ecool == false && (mx <= (Display.getWidth() - MapWidth) || (mx == 0 && px <= Display.getWidth()/2 - 64)) && px < Display.getWidth() - 100) {
			px +=hs;
		} else if(kb.isWdown() && !kb.isAdown() && !kb.isDdown() && !kb.isSdown() && ncool == false && (my == 0 || (my <= (Display.getHeight() - MapHeight) && py >= Display.getHeight()/2 - 64)) && py > 0) {
			py -=vs;
		} else if(kb.isSdown() && !kb.isAdown() && !kb.isDdown() && !kb.isWdown() && scool == false && (my <= (Display.getHeight() - MapHeight) || (my == 0 && py <= Display.getHeight()/2 - 64)) && py < Display.getHeight() - 90) {
			py +=vs;
		}
		/*if(kb.isAdown() && px > 0) {
			px -= hs;
		} else if(kb.isDdown() && px + 100 < 800) {
			px += hs;
		} else if(kb.isWdown() && py > 0) {
			py -= vs;
		} else if(kb.isSdown() && py + 100 < 600) {
			py += vs;
		} else {
			py += 0;
			px += 0;
		}*/
	}
	
	public void destroy() {
		System.out.println("Unbinding");
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		System.out.println("Unbinded texture " + texture.getTextureID());
	}
	
	public void setMX(int mx) {
		this.mx = mx;
	}
	public void setMY(int my) {
		this.my = my;
	}

	public int getPx() {
		return px;
	}

	public int getPy() {
		return py;
	}
	
	public boolean isCenteredv() {
		return centeredv;
	}
	
	public boolean isCenteredh() {
		return centeredh;
	}
	
	public void setCollisions(boolean[] cool) {
		if(cool[2]) {
			ncool = true;
		} else {
			ncool = false;
		}
		if(cool[0]) {
			scool = true;
			//System.out.println("COLLIDED TO THE SOUTH OF PLAYER");
		} else {
			scool = false;
		}
		if(cool[1]) {
			wcool = true;
			//System.out.println("COLLIDED TO THE WEST OF PLAYER");
		} else {
			wcool = false;
		}
		if(cool[3]) {
			ecool = true;
		} else {
			ecool = false;
		}
		if(ecool && wcool && scool & ncool) {
			living = false;
		}
	}
	
	public boolean isLiving() {
		return living;
	}
	
	public void setPX(int px) {
		this.px = px;
	}
	
	public void setPY(int py) {
		this.py = py;
	}
	
	public void setLiving(boolean living) {
		this.living = living;
	}
	
	// Looking right 
	/*
	 * GL11.glTexCoord2f(0,1);
				GL11.glVertex2f(px,py);
				GL11.glTexCoord2f(0,0);
				GL11.glVertex2f(px+100,py);
				GL11.glTexCoord2f(1,0);
				GL11.glVertex2f(px+100,py+100);
				GL11.glTexCoord2f(1,1);
				GL11.glVertex2f(px,py+100);
	 */
	
	// Looking Left
	/*
	 * GL11.glTexCoord2f(1,0);
				GL11.glVertex2f(px,py);
				GL11.glTexCoord2f(1,1);
				GL11.glVertex2f(px+100,py);
				GL11.glTexCoord2f(0,1);
				GL11.glVertex2f(px+100,py+100);
				GL11.glTexCoord2f(0,0);
				GL11.glVertex2f(px,py+100);
	 */
	
	//Looking Down
	/*
	 * GL11.glBegin(GL11.GL_QUADS); // 0,0 x,y 1x x + widht 1y y + height // 
				GL11.glTexCoord2f(1,1);
				GL11.glVertex2f(px,py);
				GL11.glTexCoord2f(0,1);
				GL11.glVertex2f(px+100,py);
				GL11.glTexCoord2f(0,0);
				GL11.glVertex2f(px+100,py+100);
				GL11.glTexCoord2f(1,0);
				GL11.glVertex2f(px,py+100);
	 */
	// Looking Up
	/*
	 * GL11.glBegin(GL11.GL_QUADS); // 0,0 x,y 1x x + widht 1y y + height // 
				GL11.glTexCoord2f(0,0);
				GL11.glVertex2f(px,py);
				GL11.glTexCoord2f(1,0);
				GL11.glVertex2f(px+100,py);
				GL11.glTexCoord2f(1,1);
				GL11.glVertex2f(px+100,py+100);
				GL11.glTexCoord2f(0,1);
				GL11.glVertex2f(px,py+100);
	 */
}

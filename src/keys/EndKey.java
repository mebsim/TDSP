package keys;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import loaders.LoadTextures;

public class EndKey {
	
	int x;
	int y;
	
	int px;
	int py;
	
	int nmx;
	int nmy;
	
	boolean notTaken = true;
	
	Texture texture;
	
	LoadTextures lt = new LoadTextures();
	
	public EndKey(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setUp() {
		getTexture();
	}
	
	public void getTexture() {
		texture = lt.getKey();
	}
	
	public void draw(int px, int py) {
		if(notTaken) {
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f(0,0);
				GL11.glVertex2f(x + nmx,y + nmy );
				GL11.glTexCoord2f(1,0);
				GL11.glVertex2f(x + nmx+64,y + nmy );
				GL11.glTexCoord2f(1,1);
				GL11.glVertex2f(x + nmx+64,y + nmy +64);
				GL11.glTexCoord2f(0,1);
				GL11.glVertex2f(x + nmx,y + nmy +64);
			GL11.glEnd();
			checkCollision(px, py);
		}
	}
	
	public void destroy() {
		//unbind();
	}
	
	public void unbind() {
		System.out.println("Unbinding");
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		System.out.println("Unbinded texture " + texture.getTextureID());
	}
	
	public void checkCollision(int px, int py) {
		if(px + 74 > x + nmx && px < x + nmx + 74 && py + 74 > y + nmy && py < y + nmy + 74) {
			notTaken = false;
		}
	}
	
	public void MoveWithMap(int nx, int ny) {
		nmx = nx;
		nmy = ny;
	}
	
	public boolean isNotTaken() {
		return notTaken;
	}
	
}

package keys;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import loaders.LoadTextures;

public class EndDoor {
	
	int x;
	int y;
	
	int nmx;
	int nmy;
	
	int kx;
	int ky;
	
	boolean atDoorLocation = false;
	boolean exited = false;
	
	EndKey k;
	
	LoadTextures lt = new LoadTextures();
	
	Texture openD;
	Texture closeD;
	Texture ttu; // Texture to use
	
	int counter = 0;
	
	public EndDoor(int x, int y, int kx, int ky) {
		this.x = x;
		this.y = y;
		this.kx = kx;
		this.ky = ky;
	}

	public void setUp() {
		k = new EndKey(kx,ky);
		k.setUp();
		getTexture();
		ttu = closeD;
	}
	
	public void getTexture() {
		openD = lt.getDoorO();
		closeD = lt.getDoorC();
	}
	
	public void changeTextures() {
		unbind();
		ttu = openD;
	}
	
	public void draw(int px, int py) {
		k.draw(px,py);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ttu.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x + nmx,y + nmy);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+100 + nmx,y + nmy);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+100 + nmx,y+100 + nmy);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x+ nmx,y+100+ nmy);
		GL11.glEnd();
		if(k.isNotTaken() == false && counter == 0) {
			changeTextures();
			counter ++;
		}
		if(k.isNotTaken() != true && atDoorLocation) {
			exited = true;
			System.out.println("DONE LEVEL");
		}
		checkCollision(px,py);
	}
	
	public void destroy() {
		k.destroy();
		unbind();
	}
	
	public void unbind() {
		System.out.println("Unbinding");
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		System.out.println("Unbinded texture " + ttu.getTextureID());
	}
	
	public void checkCollision(int px, int py) {
		if(px + 110 > x + nmx && px < x + nmx + 110 && py + 110 > y + nmy && py < y + nmy + 110) {
			atDoorLocation = true;
		} else {
			atDoorLocation = false;
		}
	}
	
	public void MoveWithMap(int nx, int ny) {
		nmx = nx;
		nmy = ny;
		k.MoveWithMap(nx, ny);
	}
	
	public boolean isExited() {
		return exited;
	}
	
	public void setKeyTaken(boolean notTaken) {
		k.setnotTaken(notTaken);
	}
	
}

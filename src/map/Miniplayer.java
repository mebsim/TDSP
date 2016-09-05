package map;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import loaders.LoadTextures;

public class Miniplayer {

	int px;
	int py;
	
	Texture redot;
	
	LoadTextures lt = new LoadTextures();
	
	public void getTexture() {
		redot = lt.getRedot();
	}
	
	public void draw() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, redot.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2d(0,0);
			GL11.glVertex2d(px,py);
			GL11.glTexCoord2d(1,0);
			GL11.glVertex2d(px+redot.getImageWidth()/2,py);
			GL11.glTexCoord2d(1,1);
			GL11.glVertex2d(px+redot.getImageWidth()/2,py+redot.getImageHeight()/2);
			GL11.glTexCoord2d(0,1);
			GL11.glVertex2d(px,py+redot.getImageHeight()/2);
		GL11.glEnd();
	}
	
	public void unbind() {
		System.out.println("Unbinding");
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		System.out.println("Unbinded texture " + redot.getTextureID());
	}
	
	public void setPx(int px) {
		this.px = px;
	}
	
	public void setPy(int py) {
		this.py = py;
	}
	
}

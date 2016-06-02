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
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(px,py);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(px+redot.getImageWidth(),py);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(px+redot.getImageWidth(),py+redot.getImageHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(px,py+redot.getImageHeight());
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

package map;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Minimap {
	
	Texture MapTexture;
	
	Miniplayer mp = new Miniplayer();
	
	public Minimap(Texture MapTexture) {
		this.MapTexture = MapTexture;
		mp.getTexture();
	}

	public void draw(int px, int py) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, MapTexture.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(896,0);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(896+128,0);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(896+128,0+128);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(896,0+128);
		GL11.glEnd();
		mp.draw();
		setMiniPlayerPosition(px,py);
	}
	
	public void unbind() {
		System.out.println("Unbinding");
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		System.out.println("Unbinded texture " + MapTexture.getTextureID());
		mp.unbind();
	}
	
	public void setMiniPlayerPosition(int px, int py) {
		mp.setPx((px/8));
		mp.setPy((py/8));
	}
	
}

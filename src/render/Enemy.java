package render;

import org.newdawn.slick.opengl.Texture;

public abstract class Enemy implements GenEntity {

	protected float x;
	protected float y;
	protected Texture texture;
	int cool;
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public abstract void getTexture();
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}	
	
	public abstract void movement();
	public abstract void setUp();
	public abstract void destroy();
	public abstract void observe(int px, int py);
	public boolean[] isCollision(int px, int py){
		boolean ncool = false;
		boolean ecool = false;
		boolean wcool = false;
		boolean scool = false;
		boolean[] x = {ncool, ecool, wcool, scool};
		return x;
	}
	public abstract void draw(int x, int y);
	
	public abstract void reactToPlayer();
	
	public abstract void MapChangePosition(int nx, int ny);
	
}

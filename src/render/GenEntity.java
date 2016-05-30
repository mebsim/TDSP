package render;

import java.util.List;

import wall.Walls;

public interface GenEntity {

	public float getX();
	public float getY();
	public void setX(int x);
	public void setY(int y);
	public void setUp();
	public void destroy();
	public void draw(int x, int y);
	public void MapChangePosition(int nx, int ny);
	public boolean[] isCollision(int px, int py);
	public void setWalls(List<Walls> wallList);
	
}

package render;

import java.util.List;

import org.lwjgl.opengl.GL11;

import loaders.LoadTextures;
import wall.Walls;

public class EasyEnemy extends Enemy{
	
	LoadTextures lt = new LoadTextures();
	
	List<Walls> wallList;
	
	// 1 = up 2 = right 3 = down 4 = left 5 = destroy player
	
	int cool;
	
	int x;
	int y;
	
	boolean facingR;
	boolean facingL;
	boolean facingU;
	boolean facingD;
	int hspeed = 2;
	int vspeed = 2;
	
	int nmx;
	int nmy; // New map y
	
	int[] distances = {};
	char[] directions = {};
	int counter = 0;
	int maxCounter;
	int dtg; // Distance to go
	int dg; // Distance gone
	
	float urad;
	float urad2;
	
	public EasyEnemy(int x, int y, int[] distances, char[] directions) {
		this.x = x;
		this.y = y;
		this.distances = distances;
		this.directions = directions;
		maxCounter = distances.length;
	}
	
	@Override
	public void getTexture() {
		texture = lt.getSilver();
		
	}

	@Override
	public void setUp() {
		getTexture();
	}

	@Override
	public void destroy() {
		System.out.println("Unbinding");
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		System.out.println("Unbinded texture " + texture.getTextureID());
	}

	boolean witw = false; //wall in the way
	boolean ft = false; // Found target
	int tx;
	int ty;
	int cx;
	int cy;
	boolean reseted = true;

	@Override
	public void observe(int px, int py) {
		witw = false;
		ft = false;
		if(facingU) {
			for(Walls e : wallList) {
				if(e.getY() + nmy > py && e.getY() + nmy < y + nmy && e.getX() + e.getWidth() + nmx > x + nmx && e.getX() + nmx < x + nmx + 100) {
					witw = true;
					System.out.println("Wall is closer than the player");
				}
			}
			if(witw == false && ft == false) {
				if(px + 100 > x + nmx && px < x + nmx + 100 && py + 100 > y + nmy - 400 && py < y + nmy) {
					System.out.println("FOUND TARGET");
					ft = true;
					if(reseted == true) {
						cx = x;
						cy = y;
						reseted = false;
					}
					reseted = false;
					tx = px;
					ty = py;
				}
			}
		}
		if(facingD) {
			for(Walls e : wallList) {
				if(e.getY() + nmy < py && e.getY() + nmy > y + nmy && e.getX() + e.getWidth() + nmx > x + nmx && e.getX() + nmx < x + nmx + 100) {
					witw = true;
					System.out.println("Wall is closer than the player");
				}
			}
			if(witw == false && ft == false) {
				if(px + 100 > x + nmx && px < x + nmx + 100 && py + 100 > y + nmy && py < y + nmy + 500) {
					System.out.println("FOUND TARGET");
					ft = true;
					if(reseted == true) {
						cx = x;
						cy = y;
						reseted = false;
					}
					reseted = false;
					tx = px;
					ty = py;
				}
			}
		}
		if(facingL) {
			for(Walls e : wallList) {
				if(e.getX() + nmx > px && e.getX() + e.getWidth() + nmx < x + nmx && e.getY() + e.getHeight() + nmy > y + nmy && e.getY() + nmy < y + nmy + 100) {
					witw = true;
					System.out.println("Wall is closer than the player");
				}
			}
			if(witw == false && ft == false) {
				if(px + 100 < x + nmx - 400 && px > x + nmx && py + 100 > y + nmy && py < y + nmy) {
					System.out.println("FOUND TARGET");
					ft = true;
					if(reseted == true) {
						cx = x;
						cy = y;
						reseted = false;
					}
					reseted = false;
					tx = px;
					ty = py;
				}
			}
		}
		if(ft) {
			hspeed = 6;
			vspeed = 6;
			if(ty < y + nmy) {
				y -= vspeed;
			} else if(ty > y + nmy) {
				y += vspeed;
			}
			if(tx < x + nmx) {
				x -= hspeed;
			} else if(tx > x + nmx) {
				x += hspeed;
			}
		} else {
			hspeed = 2;
			vspeed = 2;
			if(reseted != true) {
				if(y < cy) {
					y += vspeed;
				} else if (y > cy) {
					y -= vspeed;
				}
			} else {
				movement();	
			}
		}
		System.out.println("CY: " + cy + " Y: " + y);
		if(y == cy) {
			reseted = true;
		}
		
		// observing is not complete
		// The idea is to get the enemy to go quicker when they see the player. If the player gets away, they would return to where they were before they started chassing the player
	}

	@Override
	public void draw(int px, int py) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
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
		observe(px,py);
	}

	@Override
	public void reactToPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MapChangePosition(int nx, int ny) {
		nmx = nx;
		nmy = ny;
	}

	@Override
	public void movement() {
		if(directions[counter] == 'r') {
			hspeed = 2;
			vspeed = 0;
			facingR = true;
			facingL = false;
			facingU = false;
			facingD = false;
		} else if(directions[counter] == 'l') {
			hspeed = -2;
			vspeed = 0;
			facingR = false;
			facingL = true;
			facingU = false;
			facingD = false;
		} else if(directions[counter] == 'u') {
			vspeed = -2;
			hspeed = 0;
			facingR = false;
			facingL = false;
			facingU = true;
			facingD = false;
		} else if(directions[counter] == 'd') {
			vspeed = 2;
			hspeed = 0;
			facingR = false;
			facingL = false;
			facingU = false;
			facingD = true;
		} else {
			vspeed = 0;
			hspeed = 0;
			facingR = false;
			facingL = false;
			facingU = false;
			facingD = false;
		}
		dtg = distances[counter];
		if(dg >= dtg) {
			if(counter + 1 == maxCounter) {
				counter = 0;
			} else {
				counter ++;
			}
			dg = 0;
		} else {
			x += hspeed;
			y += vspeed;
			if ((cool != 3 && facingD) || (cool != 1 && facingU) || (cool != 2 && facingR) || (cool != 4 && facingL)) {
				dg += 2;
			}
		}
		// System.out.println("DG " + dg);
		// System.out.println("DTG " + dtg);
		// System.out.println("DIRECTIONS " + directions[counter]);
		// System.out.println("COUNTER " + counter);
	}

	public boolean[] isCollision(int px, int py) {
		boolean ncool = false;
		boolean wcool = false;
		boolean ecool = false;
		boolean scool = false;
		if(px + 110 > x + nmx && px < x + nmx + 110 && py + 110 > y + nmy && py < y + nmy + 110) {
			ncool = true;
			wcool = true;
			ecool = true;
			scool = true;
			System.out.println("Killed the Player");
		}
		boolean[] cool = {ncool, ecool, scool, wcool};
		return cool;
	}

	@Override
	public void setWalls(List<Walls> wallList) {
		this.wallList = wallList;
	}

}

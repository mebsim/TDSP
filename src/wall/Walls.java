package wall;

public class Walls {

	int x;
	int y;
	int nmx;
	int nmy;
	int width;
	int height;
	
	public Walls(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean[] isCollision(int px, int py) {
		boolean ncool = false;
		boolean ecool = false;
		boolean scool = false;
		boolean wcool = false;
		if(px + 100 > x + nmx && px < x + nmx + width && py + 100 > y + nmy - 5 && py < y + nmy + 5) {
			System.out.println("Collided with north of wall");
			System.out.println("This is the player's Y: " + py + ". This is the wall's y: " + y);
			ncool = true;
		} else if(py + 100 > y + nmy && py < y + nmy + height && px > x + width + nmx - 5 && px < x + nmx + width + 5) {
			ecool = true;
		} else if(px + 100 > x + nmx && px < x + nmx + width && py > y + nmy + height - 5 && py < y + nmy + height + 5) {
			scool = true;
		} else if(py + 100 > y + nmy && py < y + nmy + height && px + 100 > x + nmx - 10 && px + 100 < x + nmx + 10) {
			wcool = true;
		}
		boolean[] collisions = {ncool, ecool, scool, wcool};
		return collisions;
	}

	public void MapChangePosition(int nx, int ny) {
		nmx = nx;
		nmy = ny;
	}
	
	public int getX() {
		int xtogive = x + nmx;
		return xtogive;
	}
	
	public int getY() {
		int ytogive = y + nmy;
		return ytogive;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}

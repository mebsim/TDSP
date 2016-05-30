package level;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import keys.EndDoor;
import loaders.LoadTextures;
import main.WindowMaker;
import player.Player;
import render.EasyEnemy;
import render.GenEntity;
import user.KB;
import wall.Walls;

public class Tutorial1 implements Levels {
	
	public static boolean coolN2;
	public static boolean coolN;
	public static boolean coolE2;
	public static boolean coolE;
	public static boolean coolS2;
	public static boolean coolS;
	public static boolean coolW2;
	public static boolean coolW;
	
	Player p;
	KB kb;
	EndDoor door;
	// Plain pa;
	List<GenEntity> renderList = new ArrayList<GenEntity>();
	List<Walls> wallList = new ArrayList<Walls>();
	
	public int x = 0;
	public int y = 0;
	
	String state = "nothing";
	
	Texture MapTexture;
	LoadTextures lt = new LoadTextures();
	
	WindowMaker display = new WindowMaker();

	public void getTexture() {
		MapTexture = lt.getMap();
	}

	@Override
	public void setUp() {
		x = 0;
		y = 0;
		getTexture();
		p = new Player(50, 200,MapTexture.getImageWidth(), MapTexture.getImageHeight());
		// pa = new Plain(850, 1150, "Copyright Mohamed Ebsim 2016, Developmental 1", 12);
		kb = new KB();
		p.getTexture();
		door = new EndDoor(855, 400, 670, 460);
		//tr.init("Mouse1");
		// renderList.add(new EasyEnemy(250,250,"silver"));
		int[] distances1 = {MapTexture.getImageWidth() - 150 - 32, MapTexture.getImageWidth() - 150 - 32};
		char[] directions1 = {'r', 'l'};
		
		int[] distances2 = {165,455,165,455};
		char[] directions2 = {'r','d','l','u'};
		
		renderList.add(new EasyEnemy(50,45,distances1,directions1));
		renderList.add(new EasyEnemy(50,MapTexture.getImageHeight() - 132 - 10, distances1, directions1));
		renderList.add(new EasyEnemy(492,235,distances2,directions2));
		wallList.add(new Walls(0,0,32,MapTexture.getImageHeight()));
		wallList.add(new Walls(0,MapTexture.getImageHeight()-32,MapTexture.getImageWidth(),32));
		wallList.add(new Walls(0,0,MapTexture.getImageWidth(),32));
		wallList.add(new Walls(MapTexture.getImageWidth()-32,0,32,MapTexture.getImageHeight()));
		wallList.add(new Walls(160,160,129,704));
		wallList.add(new Walls(417,160, 415, 64));
		wallList.add(new Walls(417,160, 64,289));
		wallList.add(new Walls(768,160,64,705));
		wallList.add(new Walls(417, 800, 415, 64));
		wallList.add(new Walls(417,575, 64, 289));
		wallList.add(new Walls(607,353,32, 318));
		//wallList.add(new Walls(0,2398,MapTexture.getImageWidth(),MapTexture.getImageHeight() - 2398));
		//renderList.add(new EndDoor(300,300,400,400, p));
		/*
		 * I plan for the Enemies to be added in this format:
		 * renderList.add(new EasyEnemy(250,250,"silver",[100,200,100,200],['r','d','l','u']));
		 * 
		 * This allows me to give the Enemy more complex routes to take, along with allowing it the possibility of allowing the coder/programmer more easier knowledge of the route, without it being a very complex set of numbers
		 */
		for(GenEntity e : renderList) {
			e.setUp();
		}
		door.setUp();
	}

	@Override
	public void destroy() {
		unbind();
		for(GenEntity e : renderList) {
			e.destroy();
		}
		//tr.unbind();
		//pa.destroy();
		
		p.destroy();
		door.destroy();
		renderList.clear();
		wallList.clear();
	}

	public void unbind() {
		System.out.println("Unbinding");
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		System.out.println("Unbinded texture " + MapTexture.getTextureID());
	}

	boolean[] coolisions;
	boolean[] finalCool = {false, false, false, false};
	
	@Override
	public void draw() {
		state = "nothing";
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, MapTexture.getTextureID());
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x,y);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+MapTexture.getImageWidth(),y);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+MapTexture.getImageWidth(),y+MapTexture.getImageHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x,y+MapTexture.getImageHeight());
		GL11.glEnd();
		door.draw(p.getPx(), p.getPy());
		door.MoveWithMap(x, y);
		p.draw();
		p.setMX(x);
		p.setMY(y);
		for(GenEntity e : renderList) {
			e.draw(p.getPx(), p.getPy());
			coolisions = e.isCollision(p.getPx(), p.getPy());
			e.MapChangePosition(x, y);
			e.setWalls(wallList);
			if(coolisions[0]) {
				finalCool[0] = true;
			}
			if(coolisions[1]) {
				finalCool[1] = true;
			}
			if(coolisions[2]) {
				finalCool[2] = true;
			}
			if(coolisions[3]) {
				finalCool[3] = true;
			}
		}
		for(Walls e1: wallList) {
			coolisions = e1.isCollision(p.getPx(), p.getPy());
			e1.MapChangePosition(x, y);
			if(coolisions[0]) {
				finalCool[0] = true;
			}
			if(coolisions[1]) {
				finalCool[1] = true;
			}
			if(coolisions[2]) {
				finalCool[2] = true;
			}
			if(coolisions[3]) {
				finalCool[3] = true;
			}
		}
		if(door.isExited()) {
			state = "titlescreen";
		}
		p.setCollisions(finalCool);
		updateLocation();
		finalCool[0] = false;
		finalCool[1] = false;
		finalCool[2] = false;
		finalCool[3] = false;
	}

	public void updateLocation() {
		if(p.isCenteredv() && kb.isDdown() && display.getWidth() <= x + MapTexture.getImageWidth() && finalCool[3] == false) {
			x -= 5;
		} else if (p.isCenteredv() && kb.isAdown() && x != 0 && finalCool[1] == false) {
			x += 5;
		}
		if(p.isCenteredh() && kb.isWdown() && y != 0 && finalCool[2] == false) {
			y += 5;
		} else if (p.isCenteredh() && kb.isSdown() && display.getHeight() <= y + MapTexture.getImageHeight() && finalCool[0] == false) {
			// System.out.println("THe Display Height is " + display.getHeight() + ". The MapTexture Image Height is " + MapTexture.getImageHeight() + ". The Y + Maptexture Image Height is " + (y + MapTexture.getImageHeight()) + ". The Y is " + y + ". The Player y is " + p.getPy());
			y -= 5;
		}
		/*
		if(display.getWidth() == x + 1000) {
			System.out.println("HAHA CAN'T MOVE RIGHT");
		} else if (x == 0) {
			System.out.println("HAHA CAN'T MOVE LEFT");
		}
		*/
	}

	@Override
	public String getState() {
		return state;
	}

}
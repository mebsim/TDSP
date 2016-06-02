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

public class Level2 implements Levels {
	
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
	List<GenEntity> renderList = new ArrayList<GenEntity>();
	List<Walls> wallList = new ArrayList<Walls>();
	
	public int x = 0;
	public int y = -425;
	
	String state = "nothing";
	
	Texture MapTexture;
	LoadTextures lt = new LoadTextures();
	
	WindowMaker display = new WindowMaker();

	public void getTexture() {
		MapTexture = lt.getLevel2Map();
	}

	@Override
	public void setUp() {
		x = 0;
		y = -425;
		getTexture();
		p = new Player(50, 425,MapTexture.getImageWidth(), MapTexture.getImageHeight());
		kb = new KB();
		p.getTexture();
		door = new EndDoor(847, 847, 865, 100);
		
		int[] distances = {MapTexture.getImageHeight()-226, MapTexture.getImageHeight()-226};
		char[] directions = {'u','d'};
		
		renderList.add(new EasyEnemy(463, 846, distances, directions));
		
		wallList.add(new Walls(0,0,32,MapTexture.getImageHeight()));
		wallList.add(new Walls(0,MapTexture.getImageHeight()-32,MapTexture.getImageWidth(),32));
		wallList.add(new Walls(0,0,MapTexture.getImageWidth(),32));
		wallList.add(new Walls(MapTexture.getImageWidth()-32,0,32,MapTexture.getImageHeight()));
		wallList.add(new Walls(0,0,416,800));
		wallList.add(new Walls(608,0,190,226));
		wallList.add(new Walls(610,417,190,608));
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
			e.setWalls(wallList);
		}
		door.setUp();
	}

	@Override
	public void destroy() {
		unbind();
		for(GenEntity e : renderList) {
			e.destroy();
		}
		p.destroy();
		door.destroy();
		wallList.clear();
		renderList.clear();
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
			GL11.glVertex2f(x+MapTexture.getTextureWidth(),y);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+MapTexture.getTextureWidth(),y+MapTexture.getTextureHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x,y+MapTexture.getTextureHeight());
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
			state = "tut1";
		}

		if(p.isLiving() == false) {
			resetLevel();
		}
		p.setCollisions(finalCool);
		updateLocation();
		finalCool[0] = false;
		finalCool[1] = false;
		finalCool[2] = false;
		finalCool[3] = false;
	}

	public void updateLocation() {
		if(p.isCenteredv() && kb.isDdown() && display.getWidth() <= x + MapTexture.getTextureWidth() && finalCool[3] == false) {
			x -= 5;
		} else if (p.isCenteredv() && kb.isAdown() && x != 0 && finalCool[1] == false) {
			x += 5;
		}
		if(p.isCenteredh() && kb.isWdown() && y != 0 && finalCool[2] == false) {
			y += 5;
		} else if (p.isCenteredh() && kb.isSdown() && display.getHeight() <= y + MapTexture.getTextureHeight() && finalCool[0] == false) {
			// System.out.println("THe Display Height is " + display.getHeight() + ". The MapTexture Image Height is " + MapTexture.getImageHeight() + ". The Y + Maptexture Image Height is " + (y + MapTexture.getImageHeight()) + ". The Y is " + y + ". The Player y is " + p.getPy());
			y -= 5;
		}
	}

	@Override
	public String getState() {
		return state;
	}
	
	public void resetLevel() {
		x = 0;
		y = 0;
		p.setPX(50);
		p.setPY(200);
		p.setLiving(true);
	}

}


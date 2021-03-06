package loaders;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class LoadTextures {
	
	Texture texture;
	
	public Texture getTexture(String filename) {
		try {
            // load texture from PNG file
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/"+filename+".png"));
         
            System.out.println("Texture loaded: "+texture);
            System.out.println(">> Image width: "+texture.getImageWidth());
            System.out.println(">> Image height: "+texture.getImageHeight());
            System.out.println(">> Texture width: "+texture.getTextureWidth());
            System.out.println(">> Texture height: "+texture.getTextureHeight());
            System.out.println(">> Texture ID: "+texture.getTextureID());
        } catch (IOException e) {
            e.printStackTrace();
        }
		return texture;
	}
	
	public Texture getPlayer() {
		Texture Player = getTexture("PlayerSprite");
		return Player;
	}
	
	public Texture getSilver() {
		Texture Silver = getTexture("silver");
		return Silver;
	}
	
	public Texture getMap() {
		Texture Map6 = getTexture("map6");
		return Map6;
	}
	
	public Texture getKey() {
		Texture Key = getTexture("Key");
		return Key;
	}
	
	public Texture getDoorO() {
		Texture DoorO = getTexture("DoorO");
		return DoorO;
	}
	
	public Texture getDoorC() {
		Texture DoorC = getTexture("DoorC");
		return DoorC;
	}
	
	public Texture getTitleScreen() {
		Texture ts = getTexture("TitleScreen");
		return ts;
	}
	
	public Texture getLevel1Map() {
		Texture l1m = getTexture("level1");
		return l1m;
	}
	
	public Texture getLevel2Map() {
		Texture l2m = getTexture("level2");
		return l2m;
	}
	
	public Texture getHowToPlay() {
		Texture htp = getTexture("HowToPlay");
		return htp;
	}
	
	public Texture getLevelSelector() {
		Texture ls = getTexture("LevelSelector");
		return ls;
	}
	
	public Texture getPause() {
		Texture p = getTexture("Pause");
		return p;
	}
	
	public Texture getWin() {
		Texture w = getTexture("Win");
		return w;
	}
	
	public Texture getLose() {
		Texture l = getTexture("Lose");
		return l;
	}
	
	public Texture getMini1() {
		Texture m1 = getTexture("mini1");
		return m1;
	}
	
	public Texture getMini2() {
		Texture m2 = getTexture("mini2");
		return m2;
	}
	
	public Texture getMini3() {
		Texture m3 = getTexture("mini3");
		return m3;
	}
	
	public Texture getRedot() {
		Texture rd = getTexture("redot");
		return rd;
	}
	
}

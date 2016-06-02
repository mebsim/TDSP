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
	
	/*Texture Player = getTexture("PlayerIcon");
	Texture Silver = getTexture("silver");
	Texture Map6 = getTexture("map6");
	Texture Key = getTexture("Key");
	Texture DoorO = getTexture("DoorO");
	Texture DoorC = getTexture("DoorC");*/
	//Texture Enemy = getTexture("EnemyIcon1");
	//Texture Map1 = getTexture("Map1");

	public Texture getSprite0() {
		Texture psprite = getTexture("sprite_0");
		return psprite;
	}
	public Texture getSprite1() {
		Texture psprite = getTexture("sprite_1");
		return psprite;
	}
	public Texture getSprite2() {
		Texture psprite = getTexture("sprite_2");
		return psprite;
	}
	public Texture getSprite3() {
		Texture psprite = getTexture("sprite_3");
		return psprite;
	}
	public Texture getSprite4() {
		Texture psprite = getTexture("sprite_4");
		return psprite;
	}
	public Texture getSprite5() {
		Texture psprite = getTexture("sprite_5");
		return psprite;
	}
	public Texture getSprite6() {
		Texture psprite = getTexture("sprite_6");
		return psprite;
	}
	public Texture getSprite7() {
		Texture psprite = getTexture("sprite_7");
		return psprite;
	}
	
	public Texture getPlayer() {
		Texture Player = getTexture("PlayerIcon");
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
	
}

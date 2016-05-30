package level;

import main.WindowMaker;

public class LevelController {
	
	public Levels currentlevel;
	
	public Levels ts = new TitleScreen();
	
	public Levels tut1 = new Tutorial1();
	
	public Levels lev1 = new Level1();
	
	public Levels lev2 = new Level2();
	
	public Levels how = new HowToPlay();
	
	WindowMaker wm = new WindowMaker();
	
	public void setLevel() {
		currentlevel = ts;
	}
	
	public void setUp() {
		//System.out.println("Setting up: " + currentlevel);
		getCurrentlevel().setUp();
	}
	
	public void draw() {
		//System.out.println("Drawing: " + currentlevel);
		getCurrentlevel().draw();
		if (currentlevel.getState().equals("tut1")) {
			currentlevel.destroy();
			currentlevel = tut1;
			currentlevel.setUp();
		} else if(currentlevel.getState().equals("level2")) {
			currentlevel.destroy();
			currentlevel = lev2;
			currentlevel.setUp();
		} else if(currentlevel.getState().equals("level1")) {
			currentlevel.destroy();
			currentlevel = lev1;
			currentlevel.setUp();
		} else if (currentlevel.getState().equals("close")) {
			currentlevel.destroy();
			wm.destroy();
		} else if(currentlevel.getState().equals("titlescreen")) {
			currentlevel.destroy();
			currentlevel = ts;
			currentlevel.setUp();
		} else if(currentlevel.getState().equals("htp")) {
			currentlevel.destroy();
			currentlevel = how;
			currentlevel.setUp();
		}
	}
	
	public void destroy() {
		//System.out.println("Destroying: " + currentlevel);
		getCurrentlevel().destroy();
	}
	
	public void changeLevel(Levels ltct) {
		setCurrentlevel(ltct);
		setUp();
		draw();
	}

	public void setCurrentlevel(Levels currentlevel) {
		System.out.println("*******Setting A New Level*********");
		if (this.currentlevel != null) {
			this.currentlevel.destroy();
		}
		this.currentlevel = currentlevel;
	}

	public Levels getCurrentlevel() {
		return currentlevel;
	}
	
}

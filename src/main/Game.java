package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import level.LevelController;

public class Game {

	public static void main(String args[]) {
		WindowMaker wm = new WindowMaker();
		wm.createWindow("Top-Down Stealth Puzzle");
		wm.prepare();
		LevelController lc = new LevelController();
		lc.setLevel();
		lc.setUp();
		while(!Display.isCloseRequested()) {
			// System.out.println("First " + lc.getCurrentlevel());
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			lc.draw();
			wm.update();
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		lc.destroy();
		wm.destroy();
	}
	
}

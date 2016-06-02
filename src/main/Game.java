package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import level.LevelController;

public class Game {

	public static void main(String args[]) {
		WindowMaker wm = new WindowMaker(); // Access to window maker
		wm.createWindow("Top-Down Stealth Puzzle"); // Creates the window
		wm.prepare(); // Prepares the window
		LevelController lc = new LevelController(); // Access to the level controller
		lc.setLevel(); // Sets the current level to title screen
		lc.setUp(); // Sets up the level
		while(!Display.isCloseRequested()) { // As long as the display is not requested to be closed
			// System.out.println("First " + lc.getCurrentlevel());
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT); // Clears the screen
			lc.draw(); // Draws the screen
			wm.update(); // Updates the window
			/*try { 
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} For Debuging*/
		}
		lc.destroy(); // Destroys the currentlevel
		wm.destroy(); // Destroys the window
	}
	
}

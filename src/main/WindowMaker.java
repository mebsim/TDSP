package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class WindowMaker {
	
	int fps = 60; // Number of Frames per Second
	int width = 800; // The width of the window
	int height = 600; // The height of the window
	
    public void createWindow(String title) {
    	try {
			Display.setDisplayMode(new DisplayMode(width, height)); // Creates the window
			Display.setTitle(title); // Sets the title for the window
			Display.create(); // Brings the window into existence
		} catch (LWJGLException e) { // If it fails, show it in the console
			System.err.println("Display failed to be created.");
			e.printStackTrace();
			Display.destroy();
			System.exit(0);
		}
    }
    
    public void update() {
    		Display.update(); // Update the screen
    		Display.sync(fps); // just use this for now, might switch later
    }
    
    public void destroy() {
    	Display.destroy(); // Destroys the Display
    	System.exit(0); // The program exits
    }
    
    public void prepare() {
    		// Enables alpha blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D); // allows textures
    	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); // Allows Alpha
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f); // Clear screen color
        	
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1); // The view point
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
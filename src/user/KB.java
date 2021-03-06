package user;

import org.lwjgl.input.Keyboard;

public class KB {

	boolean adown; // Left key
	boolean sdown; // Down key
	boolean ddown; // Right key
	boolean wdown; // Up key
	boolean onedown; // 1 key
	boolean twodown;
	boolean escdown; // Escape key

	public boolean isAdown() {
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
            //System.out.println("The left key has been pressed");
            adown = true;
        } else{
        	//System.out.println("The left key has been released");
        	adown = false;
        }
		return adown;
	}

	public boolean isSdown() {
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            //System.out.println("The down key has been pressed");
            sdown = true;
        } else {
        	sdown = false;
        }
		return sdown;
	}

	public boolean isDdown() {
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
        	//System.out.println("The right key has been pressed");
        	ddown = true;
        } else {
        	ddown = false;
        }
		return ddown;
	}

	public boolean isWdown() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            //System.out.println("The up key has been pressed");
            wdown = true;
        } else {
        	wdown = false;
        }
		return wdown;
	}
	
	public boolean is1Down() {
		if(Keyboard.isKeyDown(Keyboard.KEY_1)) {
			onedown = true;
		} else {
			onedown = false;
		}
		return onedown;
	}
	
	public boolean isEscDown() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			escdown = true;
		} else {
			escdown = false;
		}
		return escdown;
	}
	
	public boolean is2Down() {
		if(Keyboard.isKeyDown(Keyboard.KEY_2)) {
			twodown = true;
		} else {
			twodown = false;
		}
		return twodown;
	}
	
}
package user;

import org.lwjgl.input.Mouse;

public class M {

	int mx;
	int my;
	
	boolean lbutton;
	boolean rbutton;
	
	public int getMx() {
		mx = Mouse.getX();
		return mx;
	}
	
	public int getMy() {
		my = 600 - Mouse.getY();
		return my;
	}

	public boolean isLeftButton() {
		lbutton = Mouse.isButtonDown(0);
		return lbutton;
	}

	public boolean isRightButton() {
		rbutton = Mouse.isButtonDown(1);
		return rbutton;
	}
	
}

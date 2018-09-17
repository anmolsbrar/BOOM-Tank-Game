package boomGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Shell {
	
	int posX, posY;
	Image img;
	boolean visible;
	
	public Shell(int startX, int startY) {
		posX = startX;
		posY = startY;
		ImageIcon newShell = new ImageIcon("src/boomGame/images/shell.png");
		img = newShell.getImage();
		visible = true;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(posX, posY, 30, 16);
	}
	
	public void move() {
		posX = posX + 2;
		if(posX > 700) {
			visible = false;
		}
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public Image getImage() {
		return img;
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}

}

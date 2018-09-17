package boomGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player {

	int xPos, moveDistance, y, newx2, newx, left, dY;
	Image player;
	ImageIcon rImg = new ImageIcon("src/boomGame/images/tankRight.png");
	ImageIcon lImg = new ImageIcon("src/boomGame/images/tankLeft.png");
	
	static ArrayList<Shell> shells;
	int ammo = 12;
	
	
	public Player() {
		player = rImg.getImage();
		left = 150;
		xPos = 75;
		y = 270;
		newx2 = 685;
		newx = 0;
		shells = new ArrayList<Shell>();
	}

	public void move() {
		if(moveDistance != -1){
			if(left + moveDistance <= 150) {
				left = left + moveDistance;
			}
			else {
				xPos = xPos + moveDistance;
				newx2 = newx2 + moveDistance;
				newx = newx + moveDistance;
			}
		} 
		else
			if(left + moveDistance > 0) {
				left = left + moveDistance;
		}
		
	}
	
	public int getnewx() {
		return newx;
	}
	
	public int getnewx2() {
		return newx2;
	}

	public int getX() {
		return xPos;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(left, y, 180, 80);
	}

	public int getY() {
		return y;
	}
	
	public int getLeft() {
		return left;
	}

	public Image getImage() {
		return player;
	}
	
	public int getmoveDistance() {
		return moveDistance;
	}
	
	public void fire() {
		if(ammo > 0) {
			ammo--;
			Shell sh = new Shell((left + 150), 260);
			shells.add(sh);
		}
		
	}
	
	public static ArrayList<Shell> getShells() {
		return shells;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT) {
			moveDistance = - 1;
			player = lImg.getImage();
		}
		if(key == KeyEvent.VK_RIGHT) {
			moveDistance = 1;
			player = rImg.getImage();
		}
		if(key == KeyEvent.VK_SPACE) {
			fire();
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT) {
			moveDistance = 0;
		}
		if(key == KeyEvent.VK_RIGHT) {
			moveDistance = 0;
		}
	}
}

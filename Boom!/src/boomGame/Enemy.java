package boomGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {
	
	Image img;
	int xPos, yPos;
	boolean isAlive = true;
	ImageIcon enemyImage = new ImageIcon("src/boomGame/images/enemyTank.png");
	ImageIcon tankDestroy = new ImageIcon("src/boomGame/images/enemyTankDestroyed.png");
	
	public Enemy(int startX, int startY) {
		xPos = startX;
		yPos = startY;
		
		img = enemyImage.getImage();
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public Image getImage() {
		destroyed();
		return img;
	}
	
	public boolean alive() {
		return isAlive;
	}
	
	public void move(int dx, int left) {
		if(dx== 1 && !((left + dx) < 150))
		xPos = xPos - dx;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xPos, yPos, 110, 65);
	}
	
	public void destroyed() {
		if(isAlive == false) {
			img = tankDestroy.getImage();
		}
	}

}

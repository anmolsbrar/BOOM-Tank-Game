package boomGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	Player p;
	Image background;
	Timer time;
	int v = 240;
	Enemy enemy1;
	Enemy enemy2;
	boolean lost = false;
	static Font font1 = new Font("SanSerif", Font.BOLD, 20);

	public Board() {
		p = new Player();
		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon i = new ImageIcon("src/boomGame/images/background.png");
		background = i.getImage();
		time = new Timer(5, this);
		time.start();
		enemy1 = new Enemy((p.xPos + 700), 240);
		enemy2 = new Enemy(700, 240);

	}
	
	public void collision() {
		Rectangle r1 = enemy1.getBounds();
		Rectangle r2 = enemy2.getBounds();
		ArrayList<Shell> shells = Player.getShells();
		for(int count = 0; count < shells.size(); count++) {
			Shell sh = (Shell) shells.get(count);
			Rectangle sh1 = sh.getBounds();
			if(r1.intersects(sh1) && enemy1.alive()) {
				enemy1.isAlive = false;
				sh.visible = false;
			}else if(r2.intersects(sh1) && enemy2.alive()) {
				enemy2.isAlive = false;
				sh.visible = false;
			}
		}
		
		Rectangle d = p.getBounds();
		if(d.intersects(r1) || d.intersects(r2)) {
			lost = true;
		}
	}

	public void actionPerformed(ActionEvent e) {
		collision();
		ArrayList<Shell> shells = Player.getShells();
		for(int count = 0; count < shells.size(); count++) {
			Shell sh = (Shell) shells.get(count);
			if(sh.getVisible() == true)
				sh.move();
			else
				shells.remove(sh);
		}
		
		p.move();
		
		if(p.xPos > 200) {
			enemy1.move(p.getmoveDistance(), p.getLeft());
		}
		if(p.xPos > 700) {
			enemy2.move(p.getmoveDistance(), p.getLeft());
		}
		
		repaint();
	}

	public void paint(Graphics g) {
		
		if(lost) {
			System.out.println("You Lost!");
		}

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if((p.getX() - 590) % 2400 == 0)
			p.newx = 0;
		if((p.getX() - 1780) % 2400 == 0)
			p.newx2 = 0;
		
		g2d.drawImage(background, 685-p.newx2, 0, null);
		if(p.getX() >= 590) {
			g2d.drawImage(background, 685-p.newx, 0, null);
		}
		g2d.drawImage(p.getImage(), p.left, v, null);
		
		ArrayList<Shell> shells = Player.getShells();
		for(int count = 0; count < shells.size(); count++) {
			Shell sh = (Shell) shells.get(count);
			g2d.drawImage(sh.getImage(), sh.getX(), sh.getY(), null);
		}
		g2d.setFont(font1);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Ammo Left: " + p.ammo, 500, 20);
		
		if(p.xPos > 400) {
			if(enemy1.isAlive == true) {
				g2d.drawImage(enemy1.getImage(), enemy1.xPos, enemy1.getY(), null);
			}
			else if(enemy1.isAlive == false) {
				g2d.drawImage(enemy1.getImage(), enemy1.xPos, enemy1.getY(), null);
			}
		}
		if(p.xPos > 400) {
			if(enemy2.isAlive == true) {
				g2d.drawImage(enemy2.getImage(), enemy2.xPos, enemy2.getY(), null);
			}
			else if(enemy2.isAlive == false) {
				g2d.drawImage(enemy2.getImage(), enemy2.xPos, enemy2.getY(), null);
			}
		}
	}

	private class AL extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);

		}

		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		}
	}

}

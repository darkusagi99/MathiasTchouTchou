package game;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Tir extends Sprite implements Reglage  {
	private String image = "src/images/bombe.png";
	private int direction;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Tir(int x, int y, int direction) {
		super();
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		
		ImageIcon i = new ImageIcon(image);
		this.setImage(i.getImage());
		
	}
	
	public void draw(Graphics g, Board b) {
        g.drawImage(this.getImage(), this.getX(), this.getY(), b);
		
	}
	
	public void move() {
		this.y += direction * VITESSE_TIR;
		
	}
}

package game;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Alien extends Sprite {
	
	private String image = "src/images/alien.png";

	public Alien(int x, int y) {
		super();
		
		this.x = x;
		this.y = y;
		
		ImageIcon i = new ImageIcon(image);
		this.setImage(i.getImage());
		
	}
	
	public void draw(Graphics g, Board b) {
        g.drawImage(this.getImage(), this.getX(), this.getY(), b);
		
	}
	

}

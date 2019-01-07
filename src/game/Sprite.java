package game;

import java.awt.Image;

public class Sprite {

	private Image image;
	protected int x;
	protected int y;
	protected boolean visible;
	protected boolean explosant;
	protected int dx;
	
	
	public Sprite() {
		super();
		
		visible = true;
		
	}
	
	public void explose() {
		
		visible = false;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isExplosant() {
		return explosant;
	}

	public void setExplosant(boolean explosant) {
		this.explosant = explosant;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}
	
}

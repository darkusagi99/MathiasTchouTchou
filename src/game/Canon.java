package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Canon extends Sprite implements Reglage {
	
	private String image = "src/images/canon.png";
	private List<Tir> listeTir;

	public Canon(int x, int y) {
		super();
		
		this.x = x;
		this.y = y;
		
		ImageIcon i = new ImageIcon(image);
		this.setImage(i.getImage());
		
		listeTir = new ArrayList<>();
		
	}
	
	public void draw(Graphics g, Board b) {
        g.drawImage(this.getImage(), this.getX(), this.getY(), b);
        
        for(Tir currentTir : listeTir) {
        	currentTir.draw(g, b);
        	
        }
		
	}
	
	// Méthode de gestion des touches
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT && this.x > 0) {
			this.x -= VITESSE_CANON;
		}
		

		if (key == KeyEvent.VK_RIGHT && this.x < (BOARD_WIDTH - SPRITE_SIZE - 6)) {
			this.x += VITESSE_CANON;
		}
		
		if (key == KeyEvent.VK_UP) {
			tirCannon();
			
		}
		
	}
	
	// Méthode de gestion des tirs du cannon
	private void tirCannon() {
		
		if (listeTir.size() < 2) {
			Tir nouveauTir = new Tir(this.x + (SPRITE_SIZE/2), GROUND - SPRITE_SIZE - 5, -1);
			listeTir.add(nouveauTir);
			
		}
		
	}
	
	// Méthode de déplacement des tirs
	public void moveTir() {
		
		List<Tir> toRemove = new ArrayList<>();
		
        for(Tir currentTir : listeTir) {
        	

        	currentTir.y += currentTir.getDirection() * VITESSE_TIR;
        	
        	if (currentTir.y < 0) {
        		toRemove.add(currentTir);
        	}
        	
        }
        
        listeTir.removeAll(toRemove);
        
        
	}

	public List<Tir> getListeTir() {
		return listeTir;
	}

	public void setListeTir(List<Tir> listeTir) {
		this.listeTir = listeTir;
	}
	
}

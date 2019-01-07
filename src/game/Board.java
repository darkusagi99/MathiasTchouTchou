package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Reglage {

	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = -2438674874126730649L;
	
	private Dimension d;
	private boolean ingame = true;
	private List<Alien> alienList;
	private Canon canon;
	private int alienDirection;
	private boolean descendre;
	
    private Thread animator;

	public Board() {

	        initBoard();
	}

	private void initBoard() {

	        addKeyListener(new TAdapter());
	        setFocusable(true);
	        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
	        setBackground(COULEUR_FOND);
	        
	        gameInit();
	        setDoubleBuffered(true);
	}
	
	private void gameInit() {
		
		// Initialisation de la direction
		alienDirection = 1;
		
		 // Initialiser les aliens
        alienList = new ArrayList<>();
        
        for(int i=0; i < LIGNES_ALIEN; i++) {
        	
        	for(int j=0; j < COLONNES_ALIEN; j++) {
        	
        		Alien a = new Alien(60 + (j* DELTA_COLONNE), 30 + (i * DELTA_LIGNE));
        		alienList.add(a);
        	}
        }
        
        // Initialiser le canon
        canon = new Canon(BOARD_WIDTH/2, GROUND - SPRITE_SIZE - 5);
        
        // boucle de jeu
        if (animator == null || !ingame) {

            animator = new Thread(this);
            animator.start();
        }
	}
	
	
	// Gestion des mouvements
	private void moves() {
		
		// Initialisation mouvement
		descendre = Boolean.FALSE;
		
		// RemoveList
		List<Tir> tirRemove = new ArrayList<>();
		List<Alien> alienRemove = new ArrayList<>();
		
		// Mouvements des tirs
		canon.moveTir();
		
		// Move Alien
		for (Alien currentAlien : alienList) {
			currentAlien.x += alienDirection * VITESSE_ALIEN;
			
			if ((currentAlien.x > BOARD_WIDTH - SPRITE_SIZE) || (currentAlien.x < 0)) {
				this.alienDirection = this.alienDirection * -1;
				descendre = Boolean.TRUE;
				
			}
		}
		
		if (descendre == Boolean.TRUE) {
			for (Alien currentAlien : alienList) {
				currentAlien.y += VITESSE_DESCENTE;
			}
		}
		
		// Contrôle de collision des tirs - Aliens
		for(Tir currentTir : canon.getListeTir()) {
			for (Alien currentAlien : alienList) {
				if ((currentTir.x > currentAlien.x && currentTir.x < currentAlien.x + SPRITE_SIZE) &&
					(currentTir.y > currentAlien.y && currentTir.y < currentAlien.y + SPRITE_SIZE)){
					// Le tir a touché
					tirRemove.add(currentTir);
					alienRemove.add(currentAlien);
				}
			}
		}
		
		alienList.removeAll(alienRemove);
		canon.getListeTir().removeAll(tirRemove);
		
		// Contrôle de collision des tirs - Canon
		
	}
	
	@Override
	public void run() {
		// Boucle du jeu
		
		long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();


        while (ingame) {

        	moves();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAI - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            
            beforeTime = System.currentTimeMillis();
        }
		
		
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(COULEUR_FOND);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(COULEUR_SOL);

        if (ingame) {

            g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
            g.fillRect(0, GROUND, BOARD_WIDTH, GROUND_HEIGHT);
            
            for(Alien currentAlien : alienList) {
            	
            	currentAlien.draw(g, this);
            }
            
            canon.draw(g, this);
            
            //Alien a = new Alien(140, 150);
            //g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            
            //drawAliens(g);
            //drawPlayer(g);
            //drawShot(g);
            //drawBombing(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }
    
    // Gestion des appuis de touches
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            //canon.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

        	canon.keyPressed(e);

        }
    }

}

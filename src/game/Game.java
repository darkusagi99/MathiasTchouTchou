package game;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Game extends JFrame implements Reglage {

	
	 /**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = -4861499826936082082L;

	public Game() {
        initUI();
	 }

    private void initUI() {

        add(new Board());
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {

		// Hello World
		System.out.println("Bonjour");
		
		
        EventQueue.invokeLater(() -> {
            Game ex = new Game();
            ex.setVisible(true);
        });
    }


}

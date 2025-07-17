package Main;

import javax.swing.*;

public class GameFrame extends JFrame {
    
    public GameFrame(GamePanel gamePanel) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setResizable(false);
        this.add(gamePanel);
        this.pack();
        this.setVisible(true);
    }

}

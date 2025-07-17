package Input;

import java.awt.event.*;
import Main.Player;
import Main.GamePanel;

public class KeyBoard implements KeyListener {

    Player player;
    GamePanel gamePanel;


    public KeyBoard(Player player, GamePanel gamePanel) {
        this.player = player;
        this.gamePanel = gamePanel;
    }
    

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.startRight();
        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.startLeft();
        }
        else if (e.getKeyCode() == KeyEvent.VK_W) {
            player.startUp();
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.startDown();
        }


    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_D) {
            player.stopRight();
        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.stopLeft();
        }
        else if (e.getKeyCode() == KeyEvent.VK_W) {
            player.stopUp();
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.stopDown();
        }
    }

    public void keyTyped(KeyEvent e) {

    }

}

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

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.startRight();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.startLeft();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.startUp();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.startDown();
        }
        else if (e.getKeyCode() == KeyEvent.VK_X) {
            player.startShooting();
        }
        else if (e.getKeyCode() == KeyEvent.VK_Z) {
            player.rotate();
        }


    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.stopRight();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.stopLeft();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.stopUp();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.stopDown();
        }
        else if (e.getKeyCode() == KeyEvent.VK_X) {
            player.stopShooting();
        }
    }

    public void keyTyped(KeyEvent e) {

    }

}

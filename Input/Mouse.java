package Input;

import java.awt.event.MouseListener;


import java.awt.event.MouseEvent;
import Main.GamePanel;


public class Mouse implements MouseListener {

    GamePanel gamePanel;

    public Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }



    public void mousePressed(MouseEvent e) {
        gamePanel.startShooting(e.getPoint().getX(), e.getPoint().getY());

    }



    public void mouseClicked(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {
        gamePanel.stopShooting();
    }
    
}

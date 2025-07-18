package Input;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import java.awt.event.MouseEvent;
import Main.GamePanel;


public class Mouse implements MouseListener, MouseMotionListener {

    GamePanel gamePanel;

    public Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void mouseDragged(MouseEvent e) {
        gamePanel.startShooting(e.getPoint().getX(), e.getPoint().getY());

    }

    public void mouseMoved(MouseEvent e) {
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

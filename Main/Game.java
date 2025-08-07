package Main;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;
public class Game implements Runnable {


    private Thread gameThread;
    private GamePanel gPanel;

    public Game() {
        gPanel = new GamePanel();
        GameFrame gFrame = new GameFrame(gPanel);
        gPanel.requestFocus(true);

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void run() {


        double timePerFrame = 1_000_000_000.0 / Constants.FPS;
        long sTime = System.nanoTime();

        while (true) {
            
            long now = System.nanoTime();
            double deltaTime = (now - sTime);

            if (deltaTime >= timePerFrame) {
                gPanel.update(deltaTime / 1_000_000_000.0);
                gPanel.repaint();
                Toolkit.getDefaultToolkit().sync();
                sTime = now;
            }
        }

    }
}
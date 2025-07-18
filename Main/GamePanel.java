package Main;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import Input.*;

public class GamePanel extends JPanel {


    private Player player;
    private Enemy[] enemies;
    private final int enemiesNumber = 20;
    private long lastEnemySpawn;


    private boolean isShooting;
    private Point shootingPoint;


    public GamePanel() {


        player = new Player();
        shootingPoint = new Point();
        enemies = new Enemy[enemiesNumber];
        lastEnemySpawn = 0;

        this.addKeyListener(new KeyBoard(player, this));

        this.addMouseListener(new Mouse(this));
        this.addMouseMotionListener(new Mouse(this));

        this.setBackground(Color.darkGray);
        this.setMinimumSize(Constants.SCREEN_SIZE);
        this.setPreferredSize(Constants.SCREEN_SIZE);
        this.setMaximumSize(Constants.SCREEN_SIZE);
    }

    public void paint(Graphics g) {
        super.paint(g);


        g.setColor(Color.RED);

        for (int i = 0; i < player.getAmmoNumber(); i += 1) {
            ShootingParticle sp = player.getAmmo()[i];
            if (sp.isShooted()) {

                g.fillRect((int) sp.getX(), (int) sp.getY(), (int) sp.getW(), (int) sp.getH());

            }
        }

        g.setColor(Color.BLUE);


        g.fillRect((int) player.getX(),(int) player.getY(), (int) player.getW(), (int) player.getH());
    }

    public void update(double deltaTime) {

        if (isShooting) {
            player.shoot(shootingPoint.getX(), shootingPoint.getY());
        }

        player.update(deltaTime);
    }

    public void startShooting(double x, double y) {
        shootingPoint.setLocation(x, y);
        isShooting = true;
    }

    public void stopShooting() {
        isShooting = false;
    }

    public void spawnEnemy() {

        if ((System.nanoTime() - lastEnemySpawn) / 1_000_000_000.0 >= 0.5) {
            
        }

    }


    
}

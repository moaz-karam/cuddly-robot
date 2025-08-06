package Main;

import javax.swing.*;
import java.awt.*;

import Input.*;

public class GamePanel extends JPanel {


    private Player player;
    private EnemyHandler eh;
    private CollisionHandler ch;
    private final int enemiesNumber = 20;


    private boolean isShooting;
    private Point shootingPoint;


    public GamePanel() {


        player = new Player();
        shootingPoint = new Point();
        eh = new EnemyHandler(player);
        ch = new CollisionHandler(player, eh);

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





        g.setColor(player.getColor());


        g.fillRect((int) player.getX(),(int) player.getY(), (int) player.getW(), (int) player.getH());

        g.setColor(Color.RED);

        for (int i = 0; i < player.getAmmoNumber(); i += 1) {
            ShootingParticle sp = player.getShootingParticle(i);
            if (sp.isShooted()) {

                g.fillRect((int) sp.getX(), (int) sp.getY(), (int) sp.getW(), (int) sp.getH());

            }
        }


        for (int i = 0; i < eh.getEnemiesNumber(); i += 1) {

            Enemy e = eh.getEnemy(i);

            if (e.isActivated()) {
                g.setColor(e.getColor());

                g.fillRect((int) e.getX(), (int) e.getY(), (int) e.getW(), (int) e.getH());
            }
        }


        g.setFont(new Font("Arial", Font.BOLD, 24));

        g.setColor(Color.white);

        g.drawString("Score: " + Player.getScore(), 50, 50);

    }

    public void update(double deltaTime) {

        if (isShooting) {
            player.shoot(shootingPoint.getX(), shootingPoint.getY());
        }

        player.update(deltaTime);
        eh.update(deltaTime);
        ch.update();
    }

    public void startShooting(double x, double y) {
        shootingPoint.setLocation(x, y);
        isShooting = true;
    }

    public void stopShooting() {
        isShooting = false;
    }


}

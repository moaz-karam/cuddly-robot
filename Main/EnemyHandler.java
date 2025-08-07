package Main;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;
import java.util.HashSet;

public class EnemyHandler {

    private Stack<Enemy> deactivatedEnemies;
    private Stack<Enemy> enemiesToActivate;
    private HashSet<Enemy> activatedEnemies;
    private HashSet<Enemy> enemiesToRemove;

    private final int enemiesNumber = 5;

    private long lastEnemySpawn;
    private final double spawnTime = 0.5;

    private Random random;

    private Player player;

    public EnemyHandler(Player p) {

        random = new Random();

        this.player = p;

        startEnemies(player);

        lastEnemySpawn = 0;

    }

    public void startEnemies(Player player) {
        activatedEnemies = new HashSet<>();
        enemiesToRemove = new HashSet<>();
        deactivatedEnemies = new Stack<>();
        enemiesToActivate = new Stack<>();

        for (int i = 0; i < enemiesNumber; i += 1) {
            deactivatedEnemies.push(new FollowingEnemy());
        }
    }

    public void update(double deltaTime) {

        if ((System.nanoTime() - lastEnemySpawn) / 1_000_000_000.0 >= spawnTime) {
            spawn();
            lastEnemySpawn = System.nanoTime();
        }
        
        updateEnemies(deltaTime);
    }

    private void spawn() {

        if (!deactivatedEnemies.isEmpty()) {

            double x = random.nextDouble(-42, Constants.SCREEN_SIZE.getWidth() + 2);
            double y = random.nextDouble(-42, Constants.SCREEN_SIZE.getHeight() + 2);

            int corner = random.nextInt(0, 4);

            switch(corner) {
                case 0 :
                    x = -42;
                    break;
                case 1 :
                    x = Constants.SCREEN_SIZE.getWidth() + 2;
                    break;
                case 2 :
                    y = -42;
                    break;
                case 3 :
                    y = Constants.SCREEN_SIZE.getHeight() + 2;
                    break;
            }

            putEnemy(x, y);
        }
    }

    private void putEnemy(double x, double y) {
        Enemy e = deactivatedEnemies.pop();
        enemiesToActivate.push(e);
        e.activate(x, y);
    }
    private void putEnemy(double x, double y, double w) {
        Enemy e = deactivatedEnemies.pop();
        enemiesToActivate.push(e);
        e.activate(x, y, w);
    }

    public void addEnemyToRemove(Enemy e) {
        enemiesToRemove.add(e);
    }

    public void getHit(Enemy e) {
        e.getHit();

        if (e.getHealth() <= 0) {
            addEnemyToRemove(e);
            deathEffect(e);
            Player.addToScore(1);
        }
    }

    private void deathEffect(Enemy e) {
        if (e instanceof FollowingEnemy) {

            if ((e.getW() / 2) < 50) {
                return;
            }

            double direct = 1;
            double x = e.getX() + e.getW() / 2 - e.getW() / 4;
            double y = e.getY() + e.getH() / 2 - e.getH() / 4;

            for (int i = 0; i < 2; i += 1) {

                if (deactivatedEnemies.isEmpty()) {
                    deactivatedEnemies.push(new FollowingEnemy());
                }

                Enemy se = deactivatedEnemies.pop();
                se.deathEffect(x, y, e.getW() / 2, e.getCos(), e.getSin(), direct);
                direct *= -1;

                enemiesToActivate.push(se);
            }
        }
    }



    private void updateEnemies(double deltaTime) {

        for (Iterator<Enemy> iter = this.getEnemies(); iter.hasNext();) {

            Enemy e = iter.next();

            if (enemiesToRemove.contains(e)) {
                enemiesToRemove.remove(e);
                iter.remove();
                deactivatedEnemies.push(e);
            }
            else {
                e.update(player, deltaTime);
            }
        }
        while (!enemiesToActivate.isEmpty()) {
            activatedEnemies.add(enemiesToActivate.pop());
        }
    }

    public Iterator<Enemy> getEnemies() {
        return activatedEnemies.iterator();
    }

    public int getEnemiesNumber() {
        return enemiesNumber;
    }

}

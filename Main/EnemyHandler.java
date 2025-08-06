package Main;

import java.util.Random;

public class EnemyHandler {

    private Enemy[] enemies;
    private final int enemiesNumber = 40;

    private long lastEnemySpawn;
    private double spawnTime;

    private Random random;

    private Player player;
    private int timer;

    public EnemyHandler(Player p) {

        random = new Random();

        this.player = p;

        startEnemies(player);

        lastEnemySpawn = 0;
        spawnTime = 0.75;

        timer = 0;

    }

    public void startEnemies(Player player) {
        enemies = new Enemy[enemiesNumber];

        for (int i = 0; i < enemiesNumber; i += 1) {
            enemies[i] = new Enemy(Enemy.FOLLOWING);
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

        enemies[Math.abs(timer) % enemiesNumber].activate(x, y);
        timer += 1;
    }


    private void updateEnemies(double deltaTime) {

        for (int i = 0; i < enemiesNumber; i += 1) {
            Enemy e = enemies[i];

            if (e.isActivated()) {
                e.update(player, deltaTime);
            }

        }
    }


    public Enemy getEnemy(int i) {
        return enemies[i];
    }

    public int getEnemiesNumber() {
        return enemiesNumber;
    }

    
}

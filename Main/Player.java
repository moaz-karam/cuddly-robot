package Main;

import java.awt.Color;

public class Player implements PlayerInterface {

    /*
     *
     * Player properties, and methods are written in the PlayerInterface interface
     *
     */

    /*
     *
     *
     *  position variables
     *
     *
     */
    private double x;
    private double y;
    private double w;
    private double h;
    private double speed;
    private boolean shooting;

    /*
     *
     *
     *  movement control variables
     *
     */

    private int right;
    private int left;
    private int up;
    private int down;

    /*
     *
     *
     * The player "ammo"
     * make it 20 at first "to be calculated after"
     *
     *
     *
     */
    private ShootingParticle[] ammo;
    private int ammoNumber = 60;
    private final double timeBetweenBullets = 0.1;
    private int timer;


    private int bulletsPerShot;
    private long lastBulletTime;

    /*
     *
     * collision related
     *
     */

     private double hearts;
     private Color color;

     private static double score;

     /*
     shooting changing direction
      */
    private int shootingDirection;
    private double targetX;
    private double targetY;

    public Player() {
        x = Constants.SCREEN_SIZE.getWidth() / 2;
        y = Constants.SCREEN_SIZE.getHeight() / 2;
        w = 50;
        h = 50;
        speed = Constants.SPEED;

        right = 0;
        left = 0;
        up = 0;
        left = 0;

        ammo = new ShootingParticle[ammoNumber];

        for (int i = 0; i < ammoNumber; i += 1) {
            ammo[i] = new ShootingParticle(this);
        }

        long now = System.nanoTime();

        lastBulletTime = now;

        hearts = Constants.HEARTS_PER_WAVE;

        timer = 0;

        color = Color.BLUE;
        score = 0;
        shooting = false;
        shootingDirection = (int)mod(now, 4);
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public Color getColor() {
        return color;
    }




    public void setX(double x) {

        if (x + w > Constants.SCREEN_SIZE.getWidth()) {
            this.x = 0;
        }
        else if (x < 0) {
            this.x = Constants.SCREEN_SIZE.getWidth() - w;
        }
        else {
            this.x = x;
        }
    }

    public void setY(double y) {

        if (y + h > Constants.SCREEN_SIZE.getHeight()) {
            this.y = 0;
        }
        else if (y < 0) {
            this.y = Constants.SCREEN_SIZE.getHeight() - h;
        }
        else {
            this.y = y;
        }
    }





    public void startRight() {
        right = 1;
    }

    public void startLeft() {
        left = 1;
    }

    public void startUp() {
        up = 1;
    }

    public void startDown() {
        down = 1;
    }




    public void stopRight() {
        right = 0;
    }

    public void stopLeft() {
        left = 0;
    }

    public void stopUp() {
        up = 0;
    }

    public void stopDown() {
        down = 0;
    }


    private int getXDirect() {
        return right - left;
    }
    private int getYDirect() {
        return down - up;
    }

    private void move(double deltaTime) {

        double movingDirections = Math.abs(getXDirect()) + Math.abs(getYDirect());

        if (movingDirections != 0) {

            double speedFactor = speed / Math.sqrt(movingDirections);

            setX(x + speedFactor * getXDirect() * deltaTime);
            setY(y + speedFactor * getYDirect() * deltaTime);

        }

    }


    public void update(double deltaTime) {

        move(deltaTime);

        if (isShooting()) {
            shoot();
        }

        for (int i = 0; i < ammoNumber; i += 1) {
            ammo[i].update(deltaTime);
        }

    }

    public void startShooting() {
        shooting = true;
    }
    public void stopShooting() {
        shooting = false;
    }

    private boolean isShooting() {
        return shooting;
    }

    private void shoot() {

        long now = System.nanoTime();
        if ((now - lastBulletTime) / 1_000_000_000.0 >= timeBetweenBullets) {
            lastBulletTime = now;
            ShootingParticle sp = ammo[Math.abs(timer) % ammoNumber];
            timer += 1;
            updateTargetPoints();
            sp.shoot(targetX - x, targetY - y);
        }
    }

    /*
    right = 0
    down = 1
    left = 2
    up = 3
     */
    public void rotate() {
        shootingDirection += 1;
    }
    private double mod(double num1, double num2) {
        if (num1 < 0) {
            return num2 - (-num1 % num2);
        }
        return num1 % num2;
    }
    private void updateTargetPoints() {
        switch((int)mod(shootingDirection, 4)) {
            case 0:
                targetX = x + 1;
                targetY = y;
                break;
            case 1:
                targetX = x;
                targetY = y - 1;
                break;
            case 2:
                targetX = x - 1;
                targetY = y;
                break;
            case 3:
                targetX = x;
                targetY = y + 1;
                break;
        }
    }


    public ShootingParticle getShootingParticle(int i) {
        return ammo[i];
    }
    public int getAmmoNumber() {
        return ammoNumber;
    }


    public void getHit() {
        hearts -= 1;
        color = color.darker();
    }
    public void resetHearts() {
        hearts = Constants.HEARTS_PER_WAVE;
    }

    public static void addToScore(double i) {
        score += i;
    }

    public static double getScore() {
        return score;
    }

}

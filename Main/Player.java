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
    private int timer;

    /*
     *
     * updated tomorrow
     *
     */
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

        lastBulletTime = System.nanoTime();

        hearts = Constants.HEARTS_PER_WAVE;

        timer = 0;

        color = Color.BLUE;
        score = 0;
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

        for (int i = 0; i < ammoNumber; i += 1) {
            ammo[i].update(deltaTime);
        }

    }



    public void shoot(double xF, double yF) {


        if ((System.nanoTime() - lastBulletTime) / 1_000_000_000.0 >= Constants.TIME_BETWEEN_BULLETS) {



            double xDiff = xF - (x + 0.5 * w);
            double yDiff = yF - (y + 0.5 * h);

            double displacement = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

            if (displacement > 36) {

                ShootingParticle sp = ammo[Math.abs(timer) % ammoNumber];

                double cosTheta = xDiff / displacement;
                double sinTheta = yDiff / displacement;

                sp.shoot(cosTheta, sinTheta);
                timer += 1;


                /*
                 *
                 *
                 * to be automated
                 *
                 */


                sp = ammo[Math.abs(timer) % ammoNumber];


                double cos20 = 0.93969;
                double sin20 = 0.3420;

                /*
                 * making the other 2 bullets
                 */

                double cosTheta2 = cosTheta * cos20 - sinTheta * sin20;
                double sinTheta2 = sinTheta * cos20 + cosTheta * sin20;

                sp.shoot(cosTheta2, sinTheta2);
                timer += 1;





                sp = ammo[Math.abs(timer) % ammoNumber];


                double cosTheta3 = cosTheta * cos20 + sinTheta * sin20;
                double sinTheta3 = sinTheta * cos20 - cosTheta * sin20;

                sp.shoot(cosTheta3, sinTheta3);
                timer += 1;


                lastBulletTime = System.nanoTime();
            }

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

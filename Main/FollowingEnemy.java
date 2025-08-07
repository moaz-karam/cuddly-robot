package Main;

import java.awt.Color;

public class FollowingEnemy implements Enemy {

    /*
     * 
     * Position, width, height
     * 
     */

    private double x;
    private double y;
    private double w;
    private double h;
    private double speed;
    private double cosTheta;
    private double sinTheta;

    /*
    used to make a relation between size and speed
     */

    private final double speedWidthConstant = Constants.ENEMY_SPEED * 90.0;

    /*
     * 
     * collision related
     * 
     */

    private double health;
    private Color color;

    /*
     death effect related
     */
    private long splitTime;
    private static final double maxSplitTime = 0.25;


    public FollowingEnemy() {

        x = 0;
        y = 0;


        double randomDimension = System.nanoTime() % 81 + 50;

        w = randomDimension;
        h = randomDimension;

        speed = speedWidthConstant / w;

        health = 100;
        color = Color.GREEN;
        splitTime = 0;
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
    public double getHealth() {
        return health;
    }
    public double getCos() {
        return cosTheta;
    }
    @Override
    public double getSin() {
        return sinTheta;
    }

    public void activate(double x1, double y1) {

        x = x1;
        y = y1;

        health = 100;
        color = Color.GREEN;

        double randomDimension = System.nanoTime() % 81 + 50;

        w = randomDimension;
        h = randomDimension;

        speed = speedWidthConstant / w;
    }

    public void activate(double x1, double y1, double w1) {

        x = x1;
        y = y1;

        health = 100;
        color = Color.GREEN;

        w = w1;
        h = w1;
    }

    public void deathEffect(double x1, double y1, double w1, double cosT, double sinT, double direct) {
        activate(x1, y1, w1);
        cosTheta = -sinT * direct;
        sinTheta = cosT * direct;
        splitTime = System.nanoTime();
    }


    public void getHit() {
        color = color.darker();
        health -= Constants.BULLET_DAMAGE;
    }


    public void update(Player player, double deltaTime) {


        if ((System.nanoTime() - splitTime) / 1_000_000_000.0 >= maxSplitTime) {

            double xPlayer = player.getX() + player.getW() * 0.5;
            double yPlayer = player.getY() + player.getH() * 0.5;


            double xDiff = xPlayer - (x + w * 0.5);
            double yDiff = yPlayer - (y + h * 0.5);

            double displacement = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

            if (displacement > 0) {
                cosTheta = xDiff / displacement;
                sinTheta = yDiff / displacement;

                x += cosTheta * speed * deltaTime;
                y += sinTheta * speed * deltaTime;
            }
        }
        else {
            x += cosTheta * speed * deltaTime;
            y += sinTheta * speed * deltaTime;
        }
    }
}


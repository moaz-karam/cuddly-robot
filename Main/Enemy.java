package Main;

import java.awt.Color;

public class Enemy {
    
    /*
     * 
     * Enemy types
     * 
     * they are public because they are generated
     * in the panel itself
     * 
     * 
     */

    public static final int FOLLOWING = 0;

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

    /*
     * 
     * Action related
     * 
     */

    private int type;
    private boolean activated;

    /*
     * 
     * collision related
     * 
     */

    private double health;
    private Color color;
    

    public Enemy(int type) {

        x = 0;
        y = 0;



        double randomDimension = System.nanoTime() % 101 + 30;

        w = randomDimension;
        h = randomDimension;

        speed = Constants.SPEED;

        activated = false;
        this.type = type;

        health = 100;
        color = Color.GREEN;
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




    public void changeType(int type) {
        this.type = type;
    }

    public boolean isActivated() {
        return activated;
    }

    public void activate(double x1, double y1) {

        x = x1;
        y = y1;

        activated = true;
        health = 100;
        color = Color.GREEN;

        double randomDimension = System.nanoTime() % 81 + 50;

        w = randomDimension;
        h = randomDimension;
    }

    public void activate(double x1, double y1, double w1, double h1) {

        x = x1;
        y = y1;

        activated = true;
        health = 100;
        color = Color.GREEN;

        w = w1;
        h = h1;

    }

    public void deactivate() {
        activated = false;
        health = 100;
        color = Color.GREEN;
    }

    public void getHit() {
        if (health > 0) {
            color = color.darker();
            health -= Constants.BULLET_DAMAGE;
        }
        else {
            activated = false;
            Player.addToScore(1);
        }
    }

    private void split() {

    }



    public void update(Player player, double deltaTime) {

        if (this.type == Enemy.FOLLOWING) {
            updateFollowing(player, deltaTime);
        }
        
    }


    private void updateFollowing(Player player, double deltaTime) {

        double xPlayer = player.getX() + player.getW() * 0.5;
        double yPlayer = player.getY() + player.getH() * 0.5;


        double xDiff = xPlayer - (x + w * 0.5);
        double yDiff = yPlayer - (y + h * 0.5);

        double displacement = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

        if (displacement > 0) {
            double cosTheta = xDiff / displacement;
            double sinTheta = yDiff / displacement;

            x += cosTheta * speed * deltaTime;
            y += sinTheta * speed * deltaTime;
        }

    }
}

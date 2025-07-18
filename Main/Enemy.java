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
    
    private Player player;


    public Enemy(Player player, int type) {

        x = 0;
        y = 0;


        w = 40;
        h = 40;

        speed = Constants.SPEED;

        activated = true;
        this.type = type;

        health = 100;
        color = Color.GREEN;

        this.player = player;
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





    public boolean isActivated() {
        return activated;
    }

    public void activate() {
        activated = true;
    }

    public void update(double deltaTime) {

        if (this.type == Enemy.FOLLOWING) {
            updateFollowing(deltaTime);
        }
        
    }


    private void updateFollowing(double deltaTime) {

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

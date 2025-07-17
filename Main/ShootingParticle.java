package Main;

public class ShootingParticle {

    /*
     * 
     * used for position
     */
    private double x;
    private double y;
    private double w;
    private double h;
    private double speed;

    /*
     * 
     * used for direction
     * 
     * sin for y
     * cos for x
     * 
     */
    private double sinTheta;
    private double cosTheta;


    /*
     * 
     * used to determine wether the particle is shooted or not
     * 
     */
    private boolean shooted;
    private Player player;

    public ShootingParticle(Player player) {
        x = 0;
        y = 0;
        w = 20;
        h = 20;
        speed = Constants.BULLET_SPEED;

        shooted = false;
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


    /*
     * 
     * used for the calculation of the other 2 particles
     * in the player class
     * 
     */

    public double getCos() {
        return cosTheta;
    }

    public double getSin() {
        return sinTheta;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }








    public void shoot(double cosT, double sinT) {


        x = player.getX() + 0.5 * (player.getW() - w);
        y = player.getY() + 0.5 * (player.getH() - h);

        cosTheta = cosT;
        sinTheta = sinT;

        shooted = true;

    }
    

    public void update(double deltaTime) {

        if (shooted) {
            x += cosTheta * speed * deltaTime;
            y += sinTheta * speed * deltaTime;

            if (x + w < 0 || x > Constants.SCREEN_SIZE.getWidth()
                || y + h < 0 || y > Constants.SCREEN_SIZE.getHeight()) {
                shooted = false;
            }

        }

    }

    public void resetParticle() {
        shooted = false;
    }

    public boolean isShooted() {
        return shooted;
    }
    
}

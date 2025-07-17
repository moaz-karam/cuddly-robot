package Main;

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

        timer = 0;
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



    public void setX(double x) {

        if (x > Constants.SCREEN_SIZE.getWidth()) {
            this.x = -w;
        }
        else if (x < -w) {
            this.x = Constants.SCREEN_SIZE.getWidth();
        }
        else {
            this.x = x;
        }
    }

    public void setY(double y) {

        if (y > Constants.SCREEN_SIZE.getHeight()) {
            this.y = -h;
        }
        else if (y < -h) {
            this.y = Constants.SCREEN_SIZE.getHeight();
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
            



            ShootingParticle sp = ammo[Math.abs(timer) % ammoNumber];


            xF -= sp.getW() / 2;
            yF -= sp.getH() / 2;

            double xDiff = xF - (x + 0.5 * (w - sp.getW()));
            double yDiff = yF - (y + 0.5 * (h - sp.getH()));

            double displacement = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

            if (displacement > 36) {
                
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

            





            // ammo[Math.abs(timer) % ammoNumber].shoot(xF, yF);
            // timer += 1;

            // ammo[Math.abs(timer) % ammoNumber].shoot(xF + 50, yF + 50);
            // timer += 1;

            // ammo[Math.abs(timer) % ammoNumber].shoot(xF - 5, yF - 50);
            // timer += 1;


        }

    }

    public ShootingParticle[] getAmmo() {
        return ammo;
    }
    public int getAmmoNumber() {
        return ammoNumber;
    }

}

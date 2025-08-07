package Main;

import java.awt.Color;

public interface Enemy {


    public double getX();
    public double getY();
    public double getW();
    public double getH();
    public double getCos();
    public double getSin();

    public Color getColor();
    public double getHealth();

    public void activate(double x1, double x2);
    public void activate(double x1, double x2, double w1);

    public void getHit();
    public void update(Player p, double deltaTime);

    public void deathEffect(double x1, double y1, double w1, double cosT, double sinT, double direct);

}

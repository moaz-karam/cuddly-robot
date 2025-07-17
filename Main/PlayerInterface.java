//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Main;

public interface PlayerInterface {


    /*
     * 
     * position control
     */

    double getX();
    double getY();

    void setX(double x);
    void setY(double y);

    /*
     * 
     * Character movement control
     * 
     */
    void startRight();
    void startLeft();
    void startUp();
    void startDown();

    void stopRight();
    void stopLeft();
    void stopUp();
    void stopDown();



    void shoot(double x, double y);
}

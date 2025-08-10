package Main;
import java.awt.Toolkit;
import java.awt.Dimension;

public class Constants {
    public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static double SPEED = 600;
    public static double ENEMY_SPEED = 300;
    public static double BULLET_SPEED = 700;
    public static double BULLET_DAMAGE = 25;
    public static int FPS = 90;
    public static final int HEARTS_PER_WAVE = 3;
}

package Main;

import java.util.Iterator;

public class CollisionHandler {

    private Player player;
    private EnemyHandler eh;

    public CollisionHandler(Player p, EnemyHandler e) {
        player  = p;
        eh = e;
    }


    public void update() {

        for (Iterator<Enemy> iter = eh.getEnemies(); iter.hasNext();) {

            Enemy e = iter.next();

            double eX = e.getX();
            double eY = e.getY();
            double eW = e.getW();
            double eH = e.getH();

            /*
             * check for the player collision
             */
            if (overlap(player.getY(), player.getY() + player.getH(), eY, eY + eH)) {

                if (overlap(player.getX(), player.getX() + player.getW(), eX, eX + eW)) {
                    player.getHit();
                    Player.addToScore(-5);
                    eh.addEnemyToRemove(e);
                    continue;
                }

            }

            for (int i = 0; i < player.getAmmoNumber(); i += 1) {

                ShootingParticle sp = player.getShootingParticle(i);

                if (sp.isShot()) {

                    /*
                     * check the y overlapping
                     */

                    if (overlap(sp.getY(), sp.getY() + sp.getH(), eY, eY + eH)) {

                        /*
                         *
                         * if the y overlaps check if the x overlaps
                         */

                        if (overlap(sp.getX(), sp.getX() + sp.getW(), eX, eX + eW)) {
                            sp.resetParticle();
                            eh.getHit(e);
                            break;
                        }
                    }
                }
            }
        }

    }

    private static boolean overlap(double min1, double max1, double min2, double max2) {

        if (max1 - min1 >= max2 - min2) {
            return min2 >= min1 && min2 <= max1
                    || max2 >= min1 && max2 <= max1;
        }
        else {
            return min1 >= min2 && min1 <= max2
                    || max1 >= min2 && max1 <= max2;
        }
    }
}

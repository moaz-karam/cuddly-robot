package Main;

public class CollisionHandler {

    private Player player;
    private EnemyHandler eh;

    public CollisionHandler(Player p, EnemyHandler e) {
        player  = p;
        eh = e;
    }


    public void update() {

        for (int t = 0; t < eh.getEnemiesNumber(); t += 1) {

            Enemy e = eh.getEnemy(t);
            
            if (e.isActivated()) {


                /*
                 * 
                 * check for the player collision
                 * 
                 */
                if (overlap(player.getY(), player.getY() + player.getH(), e.getY(), e.getY() + e.getH())) {
                    if (overlap(player.getX(), player.getX() + player.getW(), e.getX(), e.getX() + e.getW())) {
                        player.getHit();
                        Player.addToScore(-5);
                        e.deactivate();
                        continue;
                    }
                }

                
                for (int i = 0; i < player.getAmmoNumber(); i += 1) {

                    ShootingParticle sp = player.getShootingParticle(i);
                    
                    if (sp.isShooted()) {

                        /*
                         * check the y overlaping
                         * 
                         */

                        if (overlap(sp.getY(), sp.getY() + sp.getH(), e.getY(), e.getY() + e.getH())) {

                            /*
                             *
                             * if the y overlaps check if the x overlaps 
                             */

                            if (overlap(sp.getX(), sp.getX() + sp.getW(), e.getX(), e.getX() + e.getW())) {
                                sp.resetParticle();
                                e.getHit();
                                break;
                            }

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

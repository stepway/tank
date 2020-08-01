package cn.stepin.tank;

/**
 * Created by stepway on 2020/7/31.
 */
public class FourDirFireStrategy implements FireStrategy {
    private static class DefaultFireStrategyHolder {
        private static final FourDirFireStrategy fireStrategy = new FourDirFireStrategy();
    }

    private FourDirFireStrategy() {

    }

    public static FourDirFireStrategy getInstance() {
        return DefaultFireStrategyHolder.fireStrategy;
    }

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + (Tank.WIDTH - Bullet.WIDTH) / 2;
        int bY = tank.y + (Tank.HEIGHT - Bullet.HEIGHT) / 2;
        for (Dir dir : Dir.values()) {
            ResourceMgr.getInstance()
                    .gameFactory.createBullet(bX, bY, dir, tank.tf, tank.group);
        }

        if (tank.group == Group.GOOD) {
            new Thread(() ->
                    new Audio("audio/tank_fire.wav").play()
            ).start();
        }
    }
}

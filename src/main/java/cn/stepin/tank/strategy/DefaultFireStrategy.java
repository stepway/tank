package cn.stepin.tank.strategy;

import cn.stepin.tank.Audio;
import cn.stepin.tank.Bullet;
import cn.stepin.tank.Group;
import cn.stepin.tank.Tank;

/**
 * Created by stepway on 2020/7/31.
 */
public class DefaultFireStrategy implements FireStrategy{
    private static class DefaultFireStrategyHolder{
        private static final DefaultFireStrategy fireStrategy = new DefaultFireStrategy();
    }

    private DefaultFireStrategy() {

    }

    public static DefaultFireStrategy getInstance(){
        return DefaultFireStrategyHolder.fireStrategy;
    }

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + (Tank.WIDTH - Bullet.WIDTH) / 2;
        int bY = tank.y + (Tank.HEIGHT - Bullet.HEIGHT) / 2;
        new Bullet(
                bX,
                bY,
                tank.dir, tank.group);
        if (tank.group == Group.GOOD) {
            new Thread(()->{new Audio("audio/tank_fire.wav").play();}).start();
        }
    }
}

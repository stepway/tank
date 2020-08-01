package cn.stepin.tank.absractfactory;

import cn.stepin.tank.*;

/**
 * Created by stepway on 2020/8/1.
 */
public class DefaultFactory extends GameFactory {
    private static class DefaultFactoryHolder {
        private static DefaultFactory defaultFactory = new DefaultFactory();
    }

    private DefaultFactory() {

    }

    public static DefaultFactory getInstance() {
        return DefaultFactoryHolder.defaultFactory;
    }

    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new Tank(x, y, dir, tf, group);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new Bullet(x, y, dir, tf, group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }
}

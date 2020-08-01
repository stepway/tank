package cn.stepin.tank.absractfactory;

import cn.stepin.tank.*;

/**
 * Created by stepway on 2020/8/1.
 */
public class RectFactory extends GameFactory {
    private static class RectFactoryHolder {
        private static RectFactory rectFactory = new RectFactory();
    }

    private RectFactory() {

    }

    public static RectFactory getInstance() {
        return RectFactoryHolder.rectFactory;
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
        return new RectExplode(x, y, tf);
    }
}

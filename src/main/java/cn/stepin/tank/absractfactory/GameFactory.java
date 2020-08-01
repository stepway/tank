package cn.stepin.tank.absractfactory;

import cn.stepin.tank.Dir;
import cn.stepin.tank.Group;
import cn.stepin.tank.TankFrame;

/**
 * Created by stepway on 2020/8/1.
 */
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group);

    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
}

package cn.stepin.tank.absractfactory;

import cn.stepin.tank.Tank;

/**
 * Created by stepway on 2020/8/1.
 */
public abstract class BaseBullet implements Drawable, Movable{
    public abstract void collideWidth(BaseTank tank);
}

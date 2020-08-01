package cn.stepin.tank.absractfactory;

import cn.stepin.tank.Dir;
import cn.stepin.tank.FireStrategy;
import cn.stepin.tank.Group;

import java.awt.*;

/**
 * Created by stepway on 2020/8/1.
 */
public abstract class BaseTank implements Drawable, Movable {
    public abstract void setMoving(boolean moving);

    public abstract Group getGroup();

    public abstract Rectangle getRect();

    public abstract void setDir(Dir dir);

    public abstract void fire(FireStrategy fireStrategy);
}

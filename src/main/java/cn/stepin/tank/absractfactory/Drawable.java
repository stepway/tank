package cn.stepin.tank.absractfactory;

import java.awt.*;

/**
 * Created by stepway on 2020/8/1.
 */
public interface Drawable {
    void paint(Graphics g);

    boolean isLiving();

    void die();
}

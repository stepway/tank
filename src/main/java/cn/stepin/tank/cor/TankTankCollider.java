package cn.stepin.tank.cor;

import cn.stepin.tank.Dir;
import cn.stepin.tank.GameObject;
import cn.stepin.tank.Tank;
import cn.stepin.tank.TankFrame;

import java.awt.*;

/**
 * Created by stepway on 2020/8/3.
 */
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            Rectangle rect1 = t1.rect;
            Rectangle rect2 = t2.rect;
            if (rect1.intersects(rect2)) {
                t1.back();
                t2.back();
            }
        }

        return true;
    }
}

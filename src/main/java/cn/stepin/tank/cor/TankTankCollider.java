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
    public void collide(GameObject o1, GameObject o2) {
        if (!(o1 instanceof Tank) || !(o2 instanceof Tank)) {
            return;
        }

        Tank t1 = (Tank) o1;
        Tank t2 = (Tank) o2;

        Rectangle rect1 = t1.rect;
        Rectangle rect2 = t2.rect;
        if (!rect1.intersects(rect2)) {
            return;
        }

        Dir d1 = t1.getDir();
        Dir d2 = t2.getDir();
        if (d1.getReverseDir() == d2) {
            t1.back();
            t2.back();
            t1.setDir(t1.getDir().getReverseDir());
            t2.setDir(t2.getDir().getReverseDir());
        } else {
            Tank t = mostCentralTank(t1, t2);
            t.back();
            t.setDir(t.getDir().getReverseDir());
        }

    }

    private Tank mostCentralTank(Tank t1, Tank t2) {
        double distance1 = Math.abs(Math.sqrt(t1.x - TankFrame.WIDTH) + Math.sqrt(t1.y - TankFrame.HEIGHT));
        double distance2 = Math.abs(Math.sqrt(t2.x - TankFrame.WIDTH) + Math.sqrt(t2.y - TankFrame.HEIGHT));
        return distance1 > distance2 ? t1 : t2;
    }
}

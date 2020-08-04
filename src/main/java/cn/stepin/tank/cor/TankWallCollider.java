package cn.stepin.tank.cor;

import cn.stepin.tank.Bullet;
import cn.stepin.tank.GameObject;
import cn.stepin.tank.Tank;
import cn.stepin.tank.Wall;

import java.awt.*;

/**
 * Created by stepway on 2020/8/3.
 */
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank)o1;
            Wall wall = (Wall) o2;

            Rectangle rect1 = tank.rect;
            Rectangle rect2 = wall.rect;
            if (rect1.intersects(rect2)) {
                tank.back();
                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}

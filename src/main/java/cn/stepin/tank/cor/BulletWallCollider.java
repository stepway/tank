package cn.stepin.tank.cor;

import cn.stepin.tank.Bullet;
import cn.stepin.tank.GameObject;
import cn.stepin.tank.Wall;

import java.awt.*;

/**
 * Created by stepway on 2020/8/3.
 */
public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet bullet = (Bullet)o1;
            Wall wall = (Wall) o2;

            Rectangle rect1 = bullet.rect;
            Rectangle rect2 = wall.rect;
            if (rect1.intersects(rect2)) {
                bullet.die();
                return false;
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}

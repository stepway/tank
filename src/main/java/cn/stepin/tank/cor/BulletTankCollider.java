package cn.stepin.tank.cor;

import cn.stepin.tank.Bullet;
import cn.stepin.tank.GameObject;
import cn.stepin.tank.Tank;

import java.awt.*;

/**
 * Created by stepway on 2020/8/3.
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet)o1;
            Tank tank = (Tank) o2;

            if(bullet.getGroup().equals(tank.getGroup())){
                return true;
            }

            Rectangle rect1 = bullet.rect;
            Rectangle rect2 = tank.rect;
            if (rect1.intersects(rect2)) {
                tank.die();
                bullet.die();
                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}

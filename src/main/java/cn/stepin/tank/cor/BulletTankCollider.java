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
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            ((Bullet) o1).collideWidth((Tank) o2);
            Bullet bullet = (Bullet)o1;
            Tank tank = (Tank) o2;

            if(bullet.getGroup().equals(tank.getGroup())){
                return;
            }

            Rectangle rect1 = bullet.rect;
            Rectangle rect2 = tank.rect;
            if (rect1.intersects(rect2)) {
                tank.die();
                bullet.die();
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            collide(o2, o1);
        } else {
            return;
        }
    }
}

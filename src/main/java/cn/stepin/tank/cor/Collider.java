package cn.stepin.tank.cor;

import cn.stepin.tank.GameObject;

/**
 * Created by stepway on 2020/8/3.
 */
public interface Collider {
    void collide(GameObject o1, GameObject o2);
}

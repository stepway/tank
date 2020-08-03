package cn.stepin.tank.cor;

import cn.stepin.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by stepway on 2020/8/3.
 */
public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }

    public void add(Collider collider) {
        colliders.add(collider);
    }

    @Override
    public void collide(GameObject o1, GameObject o2) {
        for (Collider collider : colliders) {
            collider.collide(o1, o2);
        }
    }
}

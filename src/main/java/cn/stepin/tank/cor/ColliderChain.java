package cn.stepin.tank.cor;

import cn.stepin.tank.GameObject;
import cn.stepin.tank.PropertyMgr;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by stepway on 2020/8/3.
 */
public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList();

    public ColliderChain() {
        String[] colliderPackages = PropertyMgr.getString(PropertyMgr.COLLIDERS).split(",");
        for (String colliderPackage : colliderPackages) {
            try {
                Constructor<?> declaredConstructor = Class.forName(colliderPackage).getDeclaredConstructor(null);
                Collider collider = (Collider)declaredConstructor.newInstance(null);
                add(collider);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void add(Collider collider) {
        colliders.add(collider);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (Collider collider : colliders) {
            if(!collider.collide(o1, o2)){
                return false;
            }
        }
        return true;
    }
}

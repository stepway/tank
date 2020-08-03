package cn.stepin.tank;

import cn.stepin.tank.cor.BulletTankCollider;
import cn.stepin.tank.cor.Collider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepway on 2020/8/2.
 */
public class GameModel {
    Tank myTank = new Tank(400, 500, Dir.UP, this, Group.GOOD);
    List<GameObject> gameObjects = new ArrayList<>();
    Collider collider = new BulletTankCollider();

    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get(PropertyMgr.INIT_TANK_COUNT));

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            Tank tank = new Tank(50 + 150 * i, 100, Dir.DOWN, this, Group.BAD);
            tank.setMoving(true);
            add(tank);
        }
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量：" + bullets.size(), 10, 40);
//        g.drawString("敌人的数量：" + tanks.size(), 10, 60);
//        g.drawString("爆炸的数量：" + explodes.size(), 10, 80);
        g.setColor(c);


        if (myTank.isLiving()) {
            myTank.paint(g);
        }

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }

        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j=i+1; j < gameObjects.size(); j++) {
                collider.collide(gameObjects.get(i), gameObjects.get(j));
            }
        }

    }

    public Tank getMainTank() {
        return myTank;
    }
}

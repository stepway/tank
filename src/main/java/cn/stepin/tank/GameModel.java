package cn.stepin.tank;

import cn.stepin.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepway on 2020/8/2.
 */
public class GameModel {
    private static GameModel gameModel = new GameModel();

    public static GameModel getInstance() {
        return gameModel;
    }

    Tank myTank = new Tank(400, 500, Dir.UP, Group.GOOD);
    List<GameObject> gameObjects = new ArrayList<>();
    ColliderChain colliderChain = new ColliderChain();

    private GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get(PropertyMgr.INIT_TANK_COUNT));

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            Tank tank = new Tank(50 + 75 * (i % 10), 80 + 75 * (i / 10), Dir.DOWN, Group.BAD);
            tank.setMoving(true);
            add(tank);
        }

        add(new Wall(150, 150, 200, 50));
        add(new Wall(500, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(500, 300, 50, 200));
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
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                colliderChain.collide(o1, o2);
            }
        }

    }

    public Tank getMainTank() {
        return myTank;
    }
}

package cn.stepin.tank;

import cn.stepin.tank.cor.ColliderChain;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepway on 2020/8/2.
 */
public class GameModel {
    private static GameModel gameModel = new GameModel();

    static {
        gameModel.init();
    }

    public static GameModel getInstance() {
        return gameModel;
    }

    Tank myTank;
    List<GameObject> gameObjects = new ArrayList<>();
    ColliderChain colliderChain = new ColliderChain();

    private GameModel() {
    }

    private void init() {
        myTank = new Tank(400, 500, Dir.UP, Group.GOOD);

        int initTankCount = Integer.parseInt((String) PropertyMgr.get(PropertyMgr.INIT_TANK_COUNT));

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            Tank tank = new Tank(50 + 75 * (i % 10), 80 + 75 * (i / 10), Dir.DOWN, Group.BAD);
            tank.setMoving(true);
        }

        new Wall(150, 150, 200, 50);
        new Wall(500, 150, 200, 50);
        new Wall(300, 300, 50, 200);
        new Wall(500, 300, 50, 200);
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


    public void save(){
        File file = new File("/tmp/tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(myTank);
            oos.writeObject(gameObjects);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        File file = new File("/tmp/tank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            myTank = (Tank) ois.readObject();
            gameObjects = (List) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

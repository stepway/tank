package cn.stepin.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by stepway on 2020/8/2.
 */
public class GameModel {
    Tank myTank = new Tank(400, 500, Dir.UP, this, Group.GOOD);
    java.util.List<Bullet> bullets = new ArrayList<>();
    java.util.List<Tank> tanks = new ArrayList<>();
    java.util.List<Explode> explodes = new ArrayList<>();

    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get(PropertyMgr.INIT_TANK_COUNT));

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            Tank tank = new Tank(50 + 150 * i, 100, Dir.DOWN, this, Group.BAD);
            tank.setMoving(true);
            tanks.add(tank);
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(), 10, 40);
        g.drawString("敌人的数量：" + tanks.size(), 10, 60);
        g.drawString("爆炸的数量：" + explodes.size(), 10, 80);
        g.setColor(c);


        if (myTank.isLiving()) {
            myTank.paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWidth(tanks.get(j));
            }
            if(myTank.isLiving()) {
                bullets.get(i).collideWidth(myTank);
            }
        }

        for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();) {
            Bullet bullet = it.next();
            if(bullet.isLiving()){
                bullet.paint(g);
            }else{
                it.remove();
            }
        }

        for(Iterator<Tank> it = tanks.iterator(); it.hasNext();) {
            Tank tank = it.next();
            if(tank.isLiving()){
                tank.paint(g);
            }else{
                it.remove();
            }
        }

        for(Iterator<Explode> it = explodes.iterator(); it.hasNext();) {
            Explode explode = it.next();
            if(explode.isLiving()){
                explode.paint(g);
            }else{
                it.remove();
            }
        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}

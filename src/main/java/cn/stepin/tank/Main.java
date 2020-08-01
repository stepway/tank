package cn.stepin.tank;

import cn.stepin.tank.absractfactory.BaseTank;

/**
 * Created by stepway on 2020/7/27.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.get(PropertyMgr.INIT_TANK_COUNT));

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            BaseTank tank = ResourceMgr.getInstance().gameFactory.createTank(50 + 150 * i, 100, Dir.DOWN, tf, Group.BAD);
            tank.setMoving(true);
            tf.tanks.add(tank);
        }

//        new Thread(() -> {
//            new Audio("audio/war1.wav").loop();
//        }).start();


        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
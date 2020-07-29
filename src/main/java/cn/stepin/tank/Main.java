package cn.stepin.tank;

/**
 * Created by stepway on 2020/7/27.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int tankSize = 5;
        //初始化敌方坦克
        for (int i = 0; i < tankSize; i++) {
            Tank tank = new Tank(50 + 150 * i, 100, Dir.DOWN, tf, Group.BAD);
            tank.setMoving(true);
            tf.tanks.add(tank);
        }
        
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
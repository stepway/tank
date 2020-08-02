package cn.stepin.tank;

/**
 * Created by stepway on 2020/7/27.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

//        new Thread(() -> {
//            new Audio("audio/war1.wav").loop();
//        }).start();


        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
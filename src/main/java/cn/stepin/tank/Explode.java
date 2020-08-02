package cn.stepin.tank;

import java.awt.*;

/**
 * Created by stepway on 2020/7/28.
 */
public class Explode {
    public static final int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();

    private boolean living = true;

    private int x, y;
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(() -> {
            new Audio("audio/explode.wav").play();
        }).start();
    }

    public boolean isLiving() {
        return living;
    }


    public void paint(Graphics g) {
        if (!living) {
            return;
        }

        g.drawImage(ResourceMgr.getInstance().explodes[step++], x, y, null);
        if (step >= ResourceMgr.getInstance().explodes.length) {
            step = 0;
            die();
        }
    }

    private void die() {
        living = false;
    }
}

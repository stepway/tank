package cn.stepin.tank;

import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by stepway on 2020/7/28.
 */
public class Explode {
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private final TankFrame tf;

    private boolean living = true;

    private int x, y;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
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

        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            step = 0;
            die();
        }
    }

    private void die() {
        living = false;
    }
}

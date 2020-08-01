package cn.stepin.tank;

import cn.stepin.tank.absractfactory.BaseExplode;
import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by stepway on 2020/7/28.
 */
public class Explode extends BaseExplode{
    public static final int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();
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

    @Override
    public boolean isLiving() {
        return living;
    }


    @Override
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

    @Override
    public void die() {
        living = false;
    }
}

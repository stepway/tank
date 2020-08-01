package cn.stepin.tank.absractfactory;

import cn.stepin.tank.Audio;
import cn.stepin.tank.ResourceMgr;
import cn.stepin.tank.TankFrame;

import java.awt.*;

/**
 * Created by stepway on 2020/8/1.
 */
public class RectExplode extends BaseExplode {
    public static final int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();
    private final TankFrame tf;

    private boolean living = true;

    private int x, y;
    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
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

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10 * step, 10 * step);
        step++;
        if (step >= 8) {
            step = 0;
            die();
        }
    }

    @Override
    public void die() {
        living = false;
    }
}

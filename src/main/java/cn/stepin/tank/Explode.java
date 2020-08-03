package cn.stepin.tank;

import java.awt.*;

/**
 * Created by stepway on 2020/7/28.
 */
public class Explode extends GameObject{
    public static final int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();
    private GameModel gm;

    private boolean living = true;

    private int step = 0;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
        new Thread(() -> {
            new Audio("audio/explode.wav").play();
        }).start();
    }

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

    private void die() {
        living = false;
        gm.remove(this);
    }
}

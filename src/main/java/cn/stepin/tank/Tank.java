package cn.stepin.tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * Created by stepway on 2020/7/28.
 */
public class Tank {
    private int x, y;
    private Dir dir = Dir.UP;

    private static final int SPEED = 2;
    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();

    private boolean moving = false;
    private TankFrame tf = null;
    private boolean living = true;
    private Group group = Group.BAD;

    private Random random = new Random();

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public boolean isLiving() {
        return living;
    }

    public Group getGroup() {
        return group;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (!living) {
            return;
        }

        BufferedImage tankImage = ResourceMgr.tankU;
        switch (dir) {
            case UP:
                tankImage = ResourceMgr.tankU;
                break;
            case DOWN:
                tankImage = ResourceMgr.tankD;
                break;
            case LEFT:
                tankImage = ResourceMgr.tankL;
                break;
            case RIGHT:
                tankImage = ResourceMgr.tankR;
                break;
        }
        g.drawImage(tankImage, x, y, null);

        move();
    }

    public void move() {
        if (!moving) {
            return;
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (random.nextInt(10) > 8) {
            fire();
        }
    }

    public void fire() {
        tf.bullets.add(new Bullet(
                x + (Tank.WIDTH - Bullet.WIDTH) / 2,
                y + (Tank.HEIGHT - Bullet.HEIGHT) / 2,
                dir, tf, group));
    }

    public void die() {
        living = false;
        tf.explodes.add(new Explode(x, y, tf));
    }
}

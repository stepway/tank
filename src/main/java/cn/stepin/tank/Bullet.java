package cn.stepin.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by stepway on 2020/7/28.
 */
public class Bullet {
    public static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private final TankFrame tf;
    private Group group;

    private boolean living = true;

    private int x,y;
    private Dir dir;

    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public boolean isLiving() {
        return living;
    }

    public Group getGroup() {
        return group;
    }

    public void paint(Graphics g) {
        if(!living){
            return;
        }
        BufferedImage bulletImage = ResourceMgr.bulletU;
        switch (dir) {
            case UP:
                bulletImage = ResourceMgr.bulletU;
                break;
            case DOWN:
                bulletImage = ResourceMgr.bulletD;
                break;
            case LEFT:
                bulletImage = ResourceMgr.bulletL;
                break;
            case RIGHT:
                bulletImage = ResourceMgr.bulletR;
                break;
        }
        g.drawImage(bulletImage, x, y, null);
        move();
    }

    private void move() {
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

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }

    public void collideWidth(Tank tank) {
        if(group.equals(tank.getGroup())){
            return;
        }
        //TODO 用一个rect来记录子弹的位置
        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect1.intersects(rect2)) {
            tank.die();
            die();
        }
    }

    private void die() {
        living = false;
    }
}

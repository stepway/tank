package cn.stepin.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by stepway on 2020/7/28.
 */
public class Bullet {
    public static final int SPEED = PropertyMgr.getInt(PropertyMgr.BULLET_SPEED);
    public static final int WIDTH = ResourceMgr.getInstance().bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.getInstance().bulletD.getHeight();

    Rectangle rect = new Rectangle();

    private final GameModel gm;
    private Group group;

    private boolean living = true;

    private int x,y;
    private Dir dir;

    public Bullet(int x, int y, Dir dir, GameModel gm, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        this.gm.bullets.add(this);
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
        BufferedImage bulletImage = ResourceMgr.getInstance().bulletU;
        switch (dir) {
            case UP:
                bulletImage = ResourceMgr.getInstance().bulletU;
                break;
            case DOWN:
                bulletImage = ResourceMgr.getInstance().bulletD;
                break;
            case LEFT:
                bulletImage = ResourceMgr.getInstance().bulletL;
                break;
            case RIGHT:
                bulletImage = ResourceMgr.getInstance().bulletR;
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

        //update rect
        rect.x = x;
        rect.y = y;

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }
    }

    public void collideWidth(Tank tank) {
        if(group.equals(tank.getGroup())){
            return;
        }

        Rectangle rect1 = rect;
        Rectangle rect2 = tank.rect;
        if (rect1.intersects(rect2)) {
            tank.die();
            die();
        }
    }

    private void die() {
        living = false;
    }
}

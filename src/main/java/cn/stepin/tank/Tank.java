package cn.stepin.tank;

import cn.stepin.tank.strategy.FireStrategy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * Created by stepway on 2020/7/28.
 */
public class Tank extends GameObject {
    public Dir dir = Dir.UP;

    private static final int SPEED = PropertyMgr.getInt(PropertyMgr.TANK_SPEED);
    public static final int WIDTH = ResourceMgr.getInstance().badTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.getInstance().badTankD.getHeight();

    public Rectangle rect = new Rectangle();

    private boolean moving = false;
    public GameModel gm = null;
    private boolean living = true;

    public Group group = Group.BAD;

    private Random random = new Random();

    public Tank(int x, int y, Dir dir, GameModel tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = tf;
        this.group = group;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
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

    @Override
    public void paint(Graphics g) {
        if (!living) {
            return;
        }

        BufferedImage tankImage = ResourceMgr.getInstance().badTankU;
        switch (dir) {
            case UP:
                tankImage = group.equals(Group.GOOD) ? ResourceMgr.getInstance().goodTankU : ResourceMgr.getInstance().badTankU;
                break;
            case DOWN:
                tankImage = group.equals(Group.GOOD) ? ResourceMgr.getInstance().goodTankD : ResourceMgr.getInstance().badTankD;
                break;
            case LEFT:
                tankImage = group.equals(Group.GOOD) ? ResourceMgr.getInstance().goodTankL : ResourceMgr.getInstance().badTankL;
                break;
            case RIGHT:
                tankImage = group.equals(Group.GOOD) ? ResourceMgr.getInstance().goodTankR : ResourceMgr.getInstance().badTankR;
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

        boundsCheck();

        //update rect
        rect.x = x;
        rect.y = y;

        if (group.equals(Group.BAD) && random()) {
            fire(ResourceMgr.getInstance().badFs);
        }

        if (group.equals(Group.BAD)) {
            randomDir();
        }
    }

    private void boundsCheck() {
        if (x < 2) {
            x = 2;
        }
        if (x > (TankFrame.GAME_WIDTH - WIDTH - 2)) {
            x = TankFrame.GAME_WIDTH - WIDTH - 2;
        }
        if (y < 32) {
            y = 32;
        }
        if (y > (TankFrame.GAME_HEIGHT - HEIGHT - 2)) {
            y = TankFrame.GAME_HEIGHT - HEIGHT - 2;
        }
    }

    private void randomDir() {
        if (random()) {
            dir = Dir.values()[random.nextInt(4)];
        }
    }

    public void fire(FireStrategy fireStrategy) {
        if (null != fireStrategy) {
            fireStrategy.fire(this);
        }
    }

    public void die() {
        living = false;
        gm.remove(this);
        gm.add(new Explode(x, y, gm));
    }

    public boolean random() {
        return random.nextInt(100) >= 95;
    }
}

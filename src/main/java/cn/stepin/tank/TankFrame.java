package cn.stepin.tank;

import cn.stepin.tank.absractfactory.BaseBullet;
import cn.stepin.tank.absractfactory.BaseExplode;
import cn.stepin.tank.absractfactory.BaseTank;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Created by stepway on 2020/7/28.
 */
public class TankFrame extends Frame {
    BaseTank myTank = ResourceMgr.getInstance().gameFactory.createTank(400, 500, Dir.UP, this, Group.GOOD);
    List<BaseBullet> bullets = new ArrayList<>();
    List<BaseTank> tanks = new ArrayList<>();
    List<BaseExplode> explodes = new ArrayList<>();

    static final int GAME_WIDTH = PropertyMgr.getInt(PropertyMgr.GAME_WIDTH);
    static final int GAME_HEIGHT = PropertyMgr.getInt(PropertyMgr.GAME_HEIGHT);
    private Image offScreenImage;


    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank_ war");
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void update(Graphics g) {
        if (null == offScreenImage) {
            offScreenImage = createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(), 10, 40);
        g.drawString("敌人的数量：" + tanks.size(), 10, 60);
        g.drawString("爆炸的数量：" + explodes.size(), 10, 80);
        g.setColor(c);


        if (myTank.isLiving()) {
            myTank.paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWidth(tanks.get(j));
            }
            if(myTank.isLiving()) {
                bullets.get(i).collideWidth(myTank);
            }
        }

        for(Iterator<BaseBullet> it = bullets.iterator(); it.hasNext();) {
            BaseBullet bullet = it.next();
            if(bullet.isLiving()){
                bullet.paint(g);
            }else{
                it.remove();
            }
        }

        for(Iterator<BaseTank> it = tanks.iterator(); it.hasNext();) {
            BaseTank tank = it.next();
            if(tank.isLiving()){
                tank.paint(g);
            }else{
                it.remove();
            }
        }

        for(Iterator<BaseExplode> it = explodes.iterator(); it.hasNext();) {
            BaseExplode explode = it.next();
            if(explode.isLiving()){
                explode.paint(g);
            }else{
                it.remove();
            }
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire(ResourceMgr.getInstance().goodFs);
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (bL) {
                myTank.setDir(Dir.LEFT);
            }
            if (bR) {
                myTank.setDir(Dir.RIGHT);
            }
            if (bU) {
                myTank.setDir(Dir.UP);
            }
            if (bD) {
                myTank.setDir(Dir.DOWN);
            }

            if(!bL && !bR && !bU && !bD){
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
            }
        }
    }


}

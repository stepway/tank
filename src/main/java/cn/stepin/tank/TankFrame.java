package cn.stepin.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Created by stepway on 2020/7/28.
 */
public class TankFrame extends Frame {
    GameModel gm = GameModel.getInstance();

    static final int GAME_WIDTH = PropertyMgr.getInt(PropertyMgr.GAME_WIDTH);
    static final int GAME_HEIGHT = PropertyMgr.getInt(PropertyMgr.GAME_HEIGHT);
    private Image offScreenImage;


    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
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
        gm.paint(g);
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
                    gm.getMainTank().fire(ResourceMgr.getInstance().goodFs);
                    break;
                case KeyEvent.VK_S:
                    gm.save();
                    break;
                case KeyEvent.VK_L:
                    gm.load();
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (bL) {
                gm.getMainTank().setDir(Dir.LEFT);
            }
            if (bR) {
                gm.getMainTank().setDir(Dir.RIGHT);
            }
            if (bU) {
                gm.getMainTank().setDir(Dir.UP);
            }
            if (bD) {
                gm.getMainTank().setDir(Dir.DOWN);
            }

            if(!bL && !bR && !bU && !bD){
                gm.getMainTank().setMoving(false);
            }else{
                gm.getMainTank().setMoving(true);
            }
        }
    }


}

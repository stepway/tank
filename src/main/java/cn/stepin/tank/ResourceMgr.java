package cn.stepin.tank;

import cn.stepin.tank.absractfactory.GameFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by stepway on 2020/7/29.
 */
public class ResourceMgr {
    private static class ResourceMgrHolder{
        private final static ResourceMgr INSTANCE = new ResourceMgr();
    }
    public BufferedImage goodTankL, goodTankR, goodTankU, goodTankD;
    public BufferedImage badTankL, badTankR, badTankU, badTankD;
    public BufferedImage bulletL, bulletR, bulletU, bulletD;

    public BufferedImage[] explodes = new BufferedImage[16];

    public FireStrategy goodFs;

    public FireStrategy badFs;

    public GameFactory gameFactory;

    private ResourceMgr(){
        load();
    }
    private void load() {
        try {
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);


            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);


            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }

            Method method = Class.forName(PropertyMgr.getString("goodFs")).getMethod("getInstance", null);
            goodFs = (FireStrategy) method.invoke(null);

            method = Class.forName(PropertyMgr.getString("badFs")).getMethod("getInstance", null);
            badFs = (FireStrategy) method.invoke(null);

            method = Class.forName(PropertyMgr.getString(PropertyMgr.GAME_FACTORY)).getMethod("getInstance", null);
            gameFactory = (GameFactory) method.invoke(null);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static ResourceMgr getInstance(){
        return ResourceMgrHolder.INSTANCE;
    }
}

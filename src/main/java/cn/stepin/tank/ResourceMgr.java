package cn.stepin.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ResourceMgr getInstance(){
        return ResourceMgrHolder.INSTANCE;
    }
}

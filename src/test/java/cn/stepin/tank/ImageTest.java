package cn.stepin.tank;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

/**
 * Created by stepway on 2020/7/29.
 */
public class ImageTest {
    @Test
    public void test() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("images/bulletD.gif");
            BufferedImage image = ImageIO.read(input);
            assertNotNull(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

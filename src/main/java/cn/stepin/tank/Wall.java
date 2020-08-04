package cn.stepin.tank;

import java.awt.*;

/**
 * Created by stepway on 2020/8/4.
 */
public class Wall extends GameObject{
    int w,h;
    public Rectangle rect;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.rect = new Rectangle(x, y, w, h);
        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(color);
    }
}

package cn.stepin.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by stepway on 2020/8/3.
 */
public abstract class GameObject implements Serializable{
    public int x, y;

    abstract public void paint(Graphics g);
}

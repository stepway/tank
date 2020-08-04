package cn.stepin.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by stepway on 2020/7/30.
 */
public class PropertyMgr {
    static Properties props = new Properties();
    public static final String INIT_TANK_COUNT = "initTankCount";
    public static final String TANK_SPEED = "tankSpeed";
    public static final String BULLET_SPEED = "bulletSpeed";
    public static final String GAME_WIDTH = "gameWidth";
    public static final String GAME_HEIGHT = "gameHeight";
    public static final String COLLIDERS = "colliders";

    private PropertyMgr(){

    }

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key) {
        return props.get(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    public static String getString(String key) {
        return props.getProperty(key);
    }
}

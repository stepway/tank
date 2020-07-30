package cn.stepin.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by stepway on 2020/7/30.
 */
public class PropertyMgr {
    static Properties props = new Properties();
    public static final String INIT_TANK_COUNT = "initTankCount";

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
}

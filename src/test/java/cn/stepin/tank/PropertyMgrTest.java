package cn.stepin.tank;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by stepway on 2020/7/30.
 */
public class PropertyMgrTest {
    @Test
    public void getValue(){
        String initTankCount = (String) PropertyMgr.get("initTankCount");
        assertNotNull(initTankCount);
        System.out.println(initTankCount);
    }
}

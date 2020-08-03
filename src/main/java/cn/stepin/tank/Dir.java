package cn.stepin.tank;

/**
 * Created by stepway on 2020/7/28.
 */

interface Reverse {
    Dir getReverseDir();
}

public enum Dir implements Reverse {
    LEFT {
        @Override
        public Dir getReverseDir() {
            return RIGHT;
        }
    },
    RIGHT {
        @Override
        public Dir getReverseDir() {
            return LEFT;
        }
    },
    UP {
        @Override
        public Dir getReverseDir() {
            return DOWN;
        }
    },
    DOWN {
        @Override
        public Dir getReverseDir() {
            return UP;
        }
    }
}

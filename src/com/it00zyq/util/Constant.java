package com.it00zyq.util;

/**
 * 常量配置类
 * @author IT00ZYQ
 * @date 2021/5/3 21:33
 **/
public interface Constant {
    /**
     * 游戏窗口的宽高
     */
    int FRAME_WIDTH = 780;
    int FRAME_HEIGHT = 810;

    /**
     * 游戏面板的宽高 需要为25的倍数
     */
    int GAME_WIDTH = 750;
    int GAME_HEIGHT = 750;

    /**
     * 蛇的最大长度
     */
    int SNAKE_MAX_LENGTH = 200;

    /**
     * 蛇的初始长度
     */
    int SNAKE_INIT_LENGTH = 3;

    /**
     * 初始刷新时间/ms
     */
    int FLUSH_TIME = 100;

    /**
     * 最快刷新时间，
     * 默认分数每增加10时，
     * 刷新时间减少5ms
     * 最多减少到50
     */
    int MIN_FLUSH_TIME = 50;

}

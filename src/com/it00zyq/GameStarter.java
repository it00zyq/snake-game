package com.it00zyq;

import javax.swing.*;
import java.awt.*;

import com.it00zyq.game.Panel;
import com.it00zyq.util.Constant;

/**
 * @author IT00ZYQ
 * @date 2021/5/3 21:03
 **/
public class GameStarter {
    /**
     * 游戏启动入口
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        // 设置标题
        frame.setTitle("IT00ZYQ - 贪吃蛇小游戏");
        // 获取屏幕宽高，设置弹出位置以及窗体大小，使游戏窗口居中
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setBounds((width - 800) / 2, (height - 800) / 2, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        // 设置窗体大小不可调
        frame.setResizable(false);
        // 设置关闭窗口时结束程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建面板并向窗体中添加面板
        com.it00zyq.game.Panel panel = new Panel();
        frame.add(panel);

        // 设置显示窗口
        frame.setVisible(true);
    }
}

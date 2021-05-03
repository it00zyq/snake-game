package com.it00zyq.game;

import com.it00zyq.util.Constant;
import com.it00zyq.util.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 面板
 * @author IT00ZYQ
 * @date 2021/5/3 21:13
 **/
public class Panel extends JPanel {

    /**
     * 蛇的每一个图片的x和y坐标
     */
    private int[] snakeX = new int[Constant.SNAKE_MAX_LENGTH];
    private int[] snakeY = new int[Constant.SNAKE_MAX_LENGTH];

    /**
     * 当前蛇的长度
     */
    private int length = Constant.SNAKE_INIT_LENGTH;

    /**
     * 当前蛇的方向
     */
    private char direction;

    /**
     * 当前食物的位置
     */
    private int foodX;
    private int foodY;

    /**
     * 当前得分
     */
    private int score;

    /**
     * 游戏开始标识
     */
    private boolean startTag;

    /**
     * 游戏结束标识
     */
    private boolean endTag;

    /**
     * 定时器, 隔一段事件刷新蛇的位置
     */
    private Timer timer;

    /**
     * 定时器时间间隔
     */
    private int time;



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 设置面板背景颜色
        this.setBackground(new Color(211, 252, 255));

        // 画一个矩形, 贪吃蛇真正运动的区域
        g.setColor(new Color(0xE1C1E2));
        g.fillRect(10,10, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

        // 根据当前方向，画蛇头，默认朝右
        switch (direction){
            case 'L' :
                Images.LEFT_IMAGE.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case 'U' :
                Images.UP_IMAGE.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case 'D' :
                Images.DOWN_IMAGE.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            default:
                Images.RIGHT_IMAGE.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }

        // 添加蛇身
        for (int i = 1; i < length; i++) {
            Images.BODY_IMAGE.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        // 添加食物
        Images.FOOD_IMAGE.paintIcon(this, g, foodX, foodY);

        // 添加游戏当前积分
        g.setColor(new Color(255, 255, 255));
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString("当前积分：" + score, 620, 40);

        // 若游戏未开始，添加提示语
        if(!startTag){
            g.setColor(new Color(97, 153, 214));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",230,350);
        }

        // 若游戏结束，添加提示语
        if(endTag){
            g.setColor(new Color(255, 29, 63));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("GAME OVER!",230,300);
            g.drawString("按下空格重新开始!",205,350);
        }
    }

    public Panel() {
        this.init();
        // 获取系统焦点
        this.setFocusable(true);

        // 添加键盘事件监听器
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                // 获取当前按下的键
                int keyCode = e.getKeyCode();

                // 按下的是空格
                if (keyCode == KeyEvent.VK_SPACE) {
                    if (endTag) {
                        // 当前游戏已结束，按下空格表示重新开始游戏

                        // 重置游戏
                        init();
                        // 修改标识
                        endTag = !endTag;
                        startTag = false;
                    } else {
                        // 当前游戏未结束，按下空格键，表示需要开始游戏

                        // 绘制面板 底层调用paintComponent
                        repaint();

                        // 修改标识
                        startTag = !startTag;
                    }
                }

                // 按下的是方向键，改变蛇的方向即可
                if (keyCode == KeyEvent.VK_A) {
                    direction = 'L';
                }
                if (keyCode == KeyEvent.VK_D) {
                    direction = 'R';
                }
                if (keyCode == KeyEvent.VK_W) {
                    direction = 'U';
                }
                if (keyCode == KeyEvent.VK_S) {
                    direction = 'D';
                }
            }
        });

        // 初始化定时器
        timer = new Timer(time, new ActionListener() {
            /**
             * 每隔Constant.FLUSH_TIME ms
             * 系统会调用一次该方法
             * @param e ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // 游戏进行中才需要刷新
                if (startTag && !endTag) {
                    // 改变蛇身位置
                    for (int i = length - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }

                    // 根据当前运动方向，改变蛇头位置
                    switch (direction){
                        case 'U' :
                            snakeY[0] = snakeY[0] - 25;
                            break;
                        case 'D' :
                            snakeY[0] = snakeY[0] + 25;
                            break;
                        case 'L' :
                            snakeX[0] = snakeX[0] - 25;
                            break;
                        default:
                            snakeX[0] = snakeX[0] + 25;
                            break;
                    }

                    // 当超出边界时，穿到另一边
                    if(snakeX[0] > Constant.GAME_WIDTH){
                        snakeX[0] = 25;
                    }
                    if(snakeX[0] < 25){
                        snakeX[0] = Constant.GAME_WIDTH;
                    }
                    if(snakeY[0] < 25){
                        snakeY[0] = Constant.GAME_HEIGHT;
                    }
                    if(snakeY[0] > Constant.GAME_HEIGHT){
                        snakeY[0] = 25;
                    }

                    // 当蛇吃到食物时，得分与蛇长度加1，并刷新食物位置
                    if (foodX == snakeX[0] && foodY == snakeY[0]) {
                        // 分数与长度都加1
                        score ++;
                        length ++;

                        // 改变食物位置
                        foodX = randomBox(0, Constant.GAME_WIDTH / 25 - 1) * 25;
                        foodY = randomBox(0, Constant.GAME_HEIGHT / 25 - 1) * 25;

                        // 判断是否需要减少刷新时间
                        if (time > Constant.MIN_FLUSH_TIME && score % 10 == 0) {
                            time -= 5;
                            timer.setDelay(time);
                            System.out.printf("【游戏得分达到: %d, 刷新时间减少为: %d】\n", score, time);
                        }
                    }

                    // 蛇头碰到自己身体时，游戏结束
                    for (int i = 1; i < length ; i++) {
                        if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                            endTag = true;
                        }
                    }

                    // 刷新页面
                    repaint();
                }
            }
        });

        // 启动定时器
        timer.start();
    }

    /**
     * 初始化函数
     */
    public void init() {
        // 初始化蛇长度
        length = Constant.SNAKE_INIT_LENGTH;

        //初始化  蛇头 坐标：
        snakeX[0] = 150;
        snakeY[0] = 275;
        //初始化 第一节身子 坐标：
        snakeX[1] = 125;
        snakeY[1] = 275;
        //初始化  第二节身子 坐标：
        snakeX[2] = 100;
        snakeY[2] = 275;
        //定义小蛇初始化方向：
        direction = 'R';
        //初始化食物的坐标：
        foodX = 275;
        foodY = 125;
        //初始化积分：
        score = 0;
        // 初始化刷新时间间隔
        time = Constant.FLUSH_TIME;
    }


    /**
     * 生成a-b的随机数
     * @param a 下限
     * @param b 上限
     * @return 随机数
     */
    public static int randomBox(int a, int b) {
        int num = (int) (Math.random() * b) + 1;
        while (num < a) {
            num = (int) (Math.random() * b) + 1;
        }
        return num;
    }

}

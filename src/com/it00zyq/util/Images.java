package com.it00zyq.util;

import javax.swing.*;
import java.net.URL;

/**
 * @author IT00ZYQ
 * @date 2021/5/3 20:24
 **/
public interface Images {

    /**
     * 封装图片的路径
     */
    URL BODY_URL = Images.class.getResource("/images/body.png");
    ImageIcon BODY_IMAGE = new ImageIcon(BODY_URL);

    URL DOWN_URL = Images.class.getResource("/images/down.png");
    ImageIcon DOWN_IMAGE = new ImageIcon(DOWN_URL);

    URL UP_URL = Images.class.getResource("/images/up.png");
    ImageIcon UP_IMAGE = new ImageIcon(UP_URL);

    URL FOOD_URL = Images.class.getResource("/images/food.png");
    ImageIcon FOOD_IMAGE = new ImageIcon(FOOD_URL);

    URL HEADER_URL = Images.class.getResource("/images/header.png");
    ImageIcon HEADER_IMAGE = new ImageIcon(HEADER_URL);

    URL LEFT_URL = Images.class.getResource("/images/left.png");
    ImageIcon LEFT_IMAGE = new ImageIcon(LEFT_URL);

    URL RIGHT_URL = Images.class.getResource("/images/right.png");
    ImageIcon RIGHT_IMAGE = new ImageIcon(RIGHT_URL);
}

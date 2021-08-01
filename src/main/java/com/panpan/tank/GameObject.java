package com.panpan.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @Date 2021/7/25 11:29
 * @Author LiuPanpan
 */
public abstract class GameObject implements Serializable {

    public int x,y;
    public abstract void paint(Graphics g);

}

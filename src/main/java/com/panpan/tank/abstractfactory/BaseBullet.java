package com.panpan.tank.abstractfactory;

import com.panpan.tank.*;

import java.awt.*;

/**
 * @Date 2021/7/18 22:14
 * @Author LiuPanpan
 * 基础子弹抽象
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);
    public abstract void collideWith(BaseTank tank);
}

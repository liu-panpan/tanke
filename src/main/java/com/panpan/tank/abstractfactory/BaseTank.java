package com.panpan.tank.abstractfactory;

import com.panpan.tank.*;

import java.awt.*;
import java.util.Random;

/**
 * @Date 2021/7/18 21:46
 * @Author LiuPanpan
 * 坦克抽象类
 */
public abstract class BaseTank {

    public int width;//坦克的宽度
    public int height;//坦克的高度
    public TankFrame tankFrame;

    public Group group = Group.BAD;

    public Rectangle rect = new Rectangle();

    protected int x;
    protected int y;
    public Dir dir;

    public abstract void paint(Graphics g);

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public abstract void die();

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}

package com.panpan.tank;

import java.awt.*;

/**
 * @Date 2021/7/21 22:28
 * @Author LiuPanpan
 * 爆炸
 */
public class Explode {
    int x;
    int y;
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();//炸弹的宽度
    public static final int HEIGHT =  ResourceMgr.explodes[0].getHeight();//炸弹的高度
    private TankFrame tankFrame;
    private int step = 0;
    public Explode(int x, int y,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
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

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step>=ResourceMgr.explodes.length){
            step = 0;
        }
    }
}

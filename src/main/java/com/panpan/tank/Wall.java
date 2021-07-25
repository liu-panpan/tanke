package com.panpan.tank;

import java.awt.*;

/**
 * @Date 2021/7/25 18:46
 * @Author LiuPanpan
 */
public class Wall extends GameObject {
    private int w,h;
    private Rectangle rectangle;

    public Wall(int x,int y,int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rectangle = new Rectangle(x, y,w, h);
        GameModel.getInstance().add(this);
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(color);
    }
}

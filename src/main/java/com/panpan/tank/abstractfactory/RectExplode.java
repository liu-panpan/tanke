package com.panpan.tank.abstractfactory;

import com.panpan.tank.Audio;
import com.panpan.tank.ResourceMgr;
import com.panpan.tank.TankFrame;

import java.awt.*;

/**
 * @Date 2021/7/21 22:28
 * @Author LiuPanpan
 * 爆炸
 */
public class RectExplode extends BaseExplode {
    int x;
    int y;
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();//炸弹的宽度
    public static final int HEIGHT =  ResourceMgr.explodes[0].getHeight();//炸弹的高度
    private TankFrame tankFrame;
    private int step = 0;
    public RectExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        new Thread(()->new Audio("audio/explode.wav").play()).start();//为爆炸添加音效
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

    @Override
    public void paint(Graphics g) {
//        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x, y, step++, step++);
        step++;
        if (step>=30){
            tankFrame.explodes.remove(this);
        }
        g.setColor(c);
    }
}

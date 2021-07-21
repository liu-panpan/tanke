package com.panpan.tank;

import java.awt.*;
import java.util.Random;

/**
 * @Date 2021/7/18 21:46
 * @Author LiuPanpan
 * 坦克类
 */
public class Tank {
    int x;
    int y;
    private Dir dir;
    private static final int SPEND = 2;
    public static final int WIDTH = ResourceMgr.tankU.getWidth();//坦克的宽度
    public static final int HEIGHT =  ResourceMgr.tankU.getHeight();//坦克的高度
    private boolean alive = true;
    private boolean moving = true;
    private TankFrame tankFrame;
    private Random random = new Random();
    private Group group = Group.BAD;

    public Tank(int x, int y, Dir dir,Group group,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!alive){
            tankFrame.badTanks.remove(this);
        }
        switch (dir){
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            default:
                break;
        }
        move();

    }

    private void move() {
        if (moving){
            switch (dir){
                case UP:
                    y -=SPEND;
                    break;
                case DOWN:
                    y+=SPEND;
                    break;
                case LEFT:
                    x-=SPEND;
                    break;
                case RIGHT:
                    x+=SPEND;
                    break;
                default:
                    break;
            }
            if (random.nextInt(10) > 8 && this.group == Group.BAD) {
                fire();
            }
        }
    }

    public void fire() {
        int x = this.x + WIDTH / 2 - Bullet.WIDTH / 2;
        int y = this.y + HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.bullets.add(new Bullet(x, y, dir,this.group, tankFrame));
        if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.alive = false;
    }
}

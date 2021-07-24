package com.panpan.tank.abstractfactory;

import com.panpan.tank.*;

import java.awt.*;
import java.util.Random;

/**
 * @Date 2021/7/18 21:46
 * @Author LiuPanpan
 * 坦克类
 */
public class RectTank extends BaseTank {
//    public Rectangle rect = new Rectangle();
//    int x;
//    int y;
//    private Dir dir;
    private static final int SPEND = PropertyMgr.getInt("tankSpeed");
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();//坦克的宽度
    public static final int HEIGHT =  ResourceMgr.goodTankU.getHeight();//坦克的高度
    private boolean alive = true;
    private boolean moving = true;
//    private TankFrame tankFrame;
    private Random random = new Random();
//    private Group group = Group.BAD;
    private FireStrategy fireStrategy;

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        this.width = WIDTH;
        this.height = HEIGHT;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        if (this.group==Group.GOOD){
            this.fireStrategy = new FourDirFireStrategy();
        }else {
            this.fireStrategy = new DefaultFireStrategy();
        }
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

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    @Override
    public void paint(Graphics g) {
        if (!alive){
            tankFrame.badTanks.remove(this);
        }
//        switch (dir){
//            case UP:
//                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
//                break;
//            case DOWN:
//                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
//                break;
//            case LEFT:
//                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
//                break;
//            case RIGHT:
//                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
//                break;
//            default:
//                break;
//        }
        Color c = g.getColor();
        g.setColor(group == Group.GOOD ? Color.RED : Color.BLUE);
        g.fillRect(x, y, width, height);
        g.setColor(c);
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
            if (random.nextInt(100) > 95 && this.group == Group.BAD) {
                fire();
                randomDir();
            }
            boundsCheck();//边界检查
            rect.x = this.x;
            rect.y = this.y;
        }
    }

    private void boundsCheck() {
        if(this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH- RectTank.WIDTH -2) x = TankFrame.GAME_WIDTH - RectTank.WIDTH -2;
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT -2 ) y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT -2;

    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(Dir.values().length)];
    }

    public void fire() {
        this.fireStrategy.fire(this);
    }

    public void die() {
        this.alive = false;
    }
}

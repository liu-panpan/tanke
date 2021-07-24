package com.panpan.tank.abstractfactory;

import com.panpan.tank.*;

import java.awt.*;

/**
 * @Date 2021/7/18 22:14
 * @Author LiuPanpan
 * 子弹类
 */
public class RectBullet extends BaseBullet {
    int x;
    int y;
    private Dir dir;
    private static final int SPEND = PropertyMgr.getInt("bulletSpeed");
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();//子弹的宽度
    public static final int HEIGHT =  ResourceMgr.bulletD.getHeight();//子弹的高度
    private boolean isAlive = true;
    private TankFrame tankFrame;
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();
    public RectBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!isAlive){
            tankFrame.bullets.remove(this);
        }else {
        Color c = g.getColor();
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, 20, 20);
        g.setColor(c);
        }

        move();
    }

    private void move() {
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
        rect.x = this.x;
        rect.y = this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) this.isAlive = false;
    }

    /**
     * 坦克和子弹碰撞检测
     * @param baseTank
     */
    @Override
    public void collideWith(BaseTank baseTank) {
        if (this.group == baseTank.getGroup()){
            return;
        }
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if(rect.intersects(baseTank.rect)) {
            baseTank.die();
            this.die();
            int eX = baseTank.getX() + baseTank.getWidth()/2 - Explode.WIDTH/2;
            int eY = baseTank.getY() + baseTank.getWidth()/2 - Explode.HEIGHT/2;
            tankFrame.explodes.add(tankFrame.gameFactory.createExplode(eX, eY, tankFrame));
        }
    }

    private void die() {
        this.isAlive = false;
    }
}

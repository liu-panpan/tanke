package com.panpan.tank.cor;

import com.panpan.tank.*;

/**
 * @Date 2021/7/25 12:00
 * @Author LiuPanpan
 */
public class TankWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
            if (tank.getRect().intersects(wall.getRectangle())) {
                tank.back();
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            collide(o2, o1);
        }
        return true;

    }
}

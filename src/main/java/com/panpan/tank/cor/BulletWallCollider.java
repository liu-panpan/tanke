package com.panpan.tank.cor;

import com.panpan.tank.*;

/**
 * @Date 2021/7/25 12:00
 * @Author LiuPanpan
 */
public class BulletWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall){
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;
            if (bullet.getGroup().equals(Group.GOOD)){
                return false;
            }
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
            if(bullet.rect.intersects(wall.getRectangle())) {
                bullet.die();
            }
        }else if (o1 instanceof Wall && o2 instanceof Bullet){
            collide(o2,o1);
        }
        return true;

    }
}

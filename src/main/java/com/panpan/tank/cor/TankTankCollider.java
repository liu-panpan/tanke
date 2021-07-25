package com.panpan.tank.cor;

import com.panpan.tank.Bullet;
import com.panpan.tank.Explode;
import com.panpan.tank.GameObject;
import com.panpan.tank.Tank;

/**
 * @Date 2021/7/25 12:00
 * @Author LiuPanpan
 */
public class TankTankCollider implements Collider{
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank tank1 = (Tank) o1;
            Tank tank2 = (Tank) o2;
//            if (tank1.getGroup() == tank2.getGroup()){
//                return;
//            }
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
            if(tank1.getRect().intersects(tank2.getRect())) {
                tank1.stop();
                tank2.stop();
            }
        }

    }
}

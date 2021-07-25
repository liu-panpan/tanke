package com.panpan.tank.cor;

import com.panpan.tank.Bullet;
import com.panpan.tank.Explode;
import com.panpan.tank.GameObject;
import com.panpan.tank.Tank;

/**
 * @Date 2021/7/25 12:00
 * @Author LiuPanpan
 */
public class BulletTankCollider implements Collider{
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            if (bullet.getGroup() == tank.getGroup()){
                return;
            }
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
            if(bullet.rect.intersects(tank.getRect())) {
                tank.die();
                bullet.die();
                int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
                int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
                bullet.gameModel.gameObjects.add(new Explode(eX, eY, bullet.gameModel));
            }
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
            collide(o2,o1);
        }

    }
}

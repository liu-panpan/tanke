package com.panpan.tank;

/**
 * @Date 2021/7/24 14:52
 * @Author LiuPanpan
 */
public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int x = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int y = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tank.getTankFrame().bullets.add(new Bullet(x, y, tank.getDir(),tank.getGroup(), tank.getTankFrame()));
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();

    }
}

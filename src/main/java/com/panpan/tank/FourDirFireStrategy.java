package com.panpan.tank;

import com.panpan.tank.abstractfactory.BaseTank;

/**
 * @Date 2021/7/24 14:52
 * @Author LiuPanpan
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(BaseTank tank) {
        int x = tank.getX() + tank.getWidth() / 2 - Bullet.WIDTH / 2;
        int y = tank.getY() + tank.getHeight() / 2 - Bullet.HEIGHT / 2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            tank.getTankFrame().bullets.add(tank.getTankFrame().gameFactory.createBullet(x, y, dir,tank.getGroup(), tank.getTankFrame()));
        }
        if(tank.getGroup() == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();

    }
}

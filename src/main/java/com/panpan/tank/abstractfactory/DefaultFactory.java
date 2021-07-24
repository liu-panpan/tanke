package com.panpan.tank.abstractfactory;

import com.panpan.tank.*;

/**
 * @Date 2021/7/24 17:39
 * @Author LiuPanpan
 */
public class DefaultFactory extends GameFactory{

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x, y, dir, group, tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Bullet(x, y, dir, group, tf);
    }
}

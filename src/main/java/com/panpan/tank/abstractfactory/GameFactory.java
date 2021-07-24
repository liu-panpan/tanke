package com.panpan.tank.abstractfactory;

import com.panpan.tank.Dir;
import com.panpan.tank.Group;
import com.panpan.tank.TankFrame;

/**
 * @Date 2021/7/24 17:39
 * @Author LiuPanpan
 */
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
}

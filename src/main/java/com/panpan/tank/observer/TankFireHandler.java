package com.panpan.tank.observer;

import com.panpan.tank.Tank;

/**
 * @Date 2021/8/1 17:31
 * @Author LiuPanpan
 */
public class TankFireHandler implements TankFireObserver{

    @Override
    public void actionOnFire(TankFireEvent e) {
        Tank t = e.getSource();
        t.fire();
    }
}

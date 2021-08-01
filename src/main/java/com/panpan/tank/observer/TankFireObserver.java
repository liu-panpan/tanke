package com.panpan.tank.observer;

import java.io.Serializable;

/**
 * @Date 2021/8/1 17:30
 * @Author LiuPanpan
 */
public interface TankFireObserver extends Serializable {
    void actionOnFire(TankFireEvent e);
}

package com.panpan.tank;

import com.panpan.tank.abstractfactory.BaseTank;

/**
 * @Date 2021/7/24 14:50
 * @Author LiuPanpan
 */
public interface FireStrategy {
    void fire(BaseTank tank);
}

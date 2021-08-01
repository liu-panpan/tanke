package com.panpan.tank.observer;

import com.panpan.tank.Tank;

/**
 * @Date 2021/8/1 17:30
 * @Author LiuPanpan
 */
public class TankFireEvent {
    private Tank tank;

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }

    public Tank getSource() {
        return tank;
    }
}

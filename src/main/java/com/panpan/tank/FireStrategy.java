package com.panpan.tank;

import java.io.Serializable;

/**
 * @Date 2021/7/24 14:50
 * @Author LiuPanpan
 */
public interface FireStrategy extends Serializable {
    void fire(Tank tank);
}

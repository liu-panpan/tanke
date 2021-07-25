package com.panpan.tank.cor;

import com.panpan.tank.GameObject;

/**
 * @Date 2021/7/25 11:58
 * @Author LiuPanpan
 */
public interface Collider {
    boolean collide(GameObject o1,GameObject o2);
}

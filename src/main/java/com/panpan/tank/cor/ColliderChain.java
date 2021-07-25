package com.panpan.tank.cor;

import com.panpan.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @Date 2021/7/25 17:51
 * @Author LiuPanpan
 */
public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }

    private void add(Collider c) {
        colliders.add(c);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collide(o1, o2)){
                return false;
            }
        }
        return true;
    }

}

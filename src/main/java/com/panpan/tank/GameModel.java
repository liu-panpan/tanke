package com.panpan.tank;

import com.panpan.tank.cor.BulletTankCollider;
import com.panpan.tank.cor.Collider;
import com.panpan.tank.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/7/25 10:15
 * @Author LiuPanpan
 */
public class GameModel {

    Collider collider = new BulletTankCollider();
    Collider collider1 = new TankTankCollider();

    public GameModel() {
        int count = Integer.parseInt(String.valueOf(PropertyMgr.get("initTankCount")));
        //初始化敌方坦克
        for(int i=0; i<count; i++) {
            add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD,this));
        }
    }

    private void add(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void remove(GameObject go) {
        this.gameObjects.remove(go);
    }

    Tank mainTank = new Tank(200,400,Dir.UP,Group.GOOD,this);
//    java.util.List<Explode> explodes = new ArrayList<>();
//    //子弹容器
//    java.util.List<Bullet> bullets = new ArrayList<>();
//    //敌方坦克容器
//    List<Tank> badTanks = new ArrayList<>();

    public List<GameObject> gameObjects = new ArrayList<>();

    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.WHITE);
//        g.drawString("子弹个数: "+bullets.size(),10,60);
//        g.drawString("敌人的数量:" + badTanks.size(), 10, 80);
//        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
//        g.setColor(c);
        mainTank.paint(g);
//        for (int i = 0; i < bullets.size(); i++) {
//            bullets.get(i).paint(g);
//        }
//
//        for (int i = 0; i < badTanks.size(); i++) {
//            badTanks.get(i).paint(g);
//        }

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }

        //互相碰撞检测
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject gameObject1 = gameObjects.get(i);
                GameObject gameObject2 = gameObjects.get(j);
                //添加碰撞器
                collider.collide(gameObject1, gameObject2);
                collider1.collide(gameObject1, gameObject2);
            }
        }

//        for(int i=0; i<bullets.size(); i++) {
//            for(int j = 0; j<badTanks.size(); j++){
//                bullets.get(i).collideWith(badTanks.get(j));
//            }
//        }
//        for (int i = 0; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }
    }
}

package com.panpan.tank;

import com.panpan.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/7/25 10:15
 * @Author LiuPanpan
 */
public class GameModel {

    private final static GameModel INSTANCE = new GameModel();

    private GameModel() {
    }

    ;

    public static GameModel getInstance() {
        return INSTANCE;
    }

    static {
        INSTANCE.init();
    }

    Tank mainTank;

    private void init() {
        mainTank = new Tank(200, 400, Dir.UP, Group.GOOD);
        int count = Integer.parseInt(String.valueOf(PropertyMgr.get("initTankCount")));
        //初始化敌方坦克
        for (int i = 0; i < count; i++) {
            new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD);
        }
        // 初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }

    ColliderChain colliderChain = new ColliderChain();

    //    public GameModel() {
//
//    }
    public Tank getMainTank() {
        return mainTank;
    }

    public void add(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void remove(GameObject go) {
        this.gameObjects.remove(go);
    }

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
                colliderChain.collide(gameObject1, gameObject2);
//                collider.collide(gameObject1, gameObject2);
//                collider1.collide(gameObject1, gameObject2);
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

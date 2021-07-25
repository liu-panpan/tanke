package com.panpan.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/7/25 10:15
 * @Author LiuPanpan
 */
public class GameModel {

    public GameModel() {
        int count = Integer.parseInt(String.valueOf(PropertyMgr.get("initTankCount")));
        //初始化敌方坦克
        for(int i=0; i<count; i++) {
            this.badTanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD,this));
        }
    }

    Tank mainTank = new Tank(200,400,Dir.UP,Group.GOOD,this);
    java.util.List<Explode> explodes = new ArrayList<>();
    //子弹容器
    java.util.List<Bullet> bullets = new ArrayList<>();
    //敌方坦克容器
    List<Tank> badTanks = new ArrayList<>();

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹个数: "+bullets.size(),10,60);
        g.drawString("敌人的数量:" + badTanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        g.setColor(c);
        mainTank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < badTanks.size(); i++) {
            badTanks.get(i).paint(g);
        }

        for(int i=0; i<bullets.size(); i++) {
            for(int j = 0; j<badTanks.size(); j++){
                bullets.get(i).collideWith(badTanks.get(j));
            }
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }
}

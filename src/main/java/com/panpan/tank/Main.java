package com.panpan.tank;

import com.panpan.tank.net.Client;

/**
 * @Date 2021/7/18 18:18
 * @Author LiuPanpan
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = TankFrame.INSTANCE;
        tankFrame.setVisible(true);
//        int count = Integer.parseInt(String.valueOf(PropertyMgr.get("initTankCount")));
//        //初始化敌方坦克
//        for(int i=0; i<count; i++) {
//            tankFrame.badTanks.add(new Tank(50 + i*80, 200, Dir.DOWN, Group.BAD,tankFrame));
//        }
//        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        new Thread(()-> {
            while(true) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tankFrame.repaint();
            }
        }).start();

        //or you can new a thread to run this
        Client.INSTANCE.connect();
    }

}

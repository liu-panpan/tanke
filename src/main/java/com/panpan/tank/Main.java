package com.panpan.tank;

/**
 * @Date 2021/7/18 18:18
 * @Author LiuPanpan
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int count = Integer.parseInt(String.valueOf(PropertyMgr.get("initTankCount")));
        //初始化敌方坦克
        for(int i=0; i<count; i++) {
            tankFrame.badTanks.add(tankFrame.gameFactory.createTank(50 + i*80, 200, Dir.DOWN, Group.BAD,tankFrame));
        }
        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        while (true){
            Thread.sleep(25);
            tankFrame.repaint();
        }
    }

}

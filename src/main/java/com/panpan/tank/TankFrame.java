package com.panpan.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/7/18 18:33
 * @Author LiuPanpan
 * tank窗口类
 */
public class TankFrame extends Frame {
    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 600;
    Tank tank = new Tank(200,400,Dir.UP,Group.GOOD,this);
    //子弹容器
    List<Bullet> bullets = new ArrayList<>();
    //敌方坦克容器
    List<Tank> badTanks = new ArrayList<>();
//    Bullet bullet = new Bullet(300,300,Dir.DOWN);

    public TankFrame() throws HeadlessException {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setTitle("tank war");
        setResizable(false);
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
//        System.out.println("paint");
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹个数: "+bullets.size(),10,60);
        g.drawString("敌人的数量:" + badTanks.size(), 10, 80);
        tank.paint(g);
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

    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //监听键盘
    class MyKeyListener extends KeyAdapter {
        //判断方向是否按下
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;
        @Override
        public void keyPressed(KeyEvent e) {
            //键盘按下的事件
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            System.out.println("key Pressed");
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //键盘抬起来的事件
            System.out.println("key Released");
            //键盘按下的事件
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL&&!bR&&!bU&&!bD){
                tank.setMoving(false);
            }else {
                Dir dir = Dir.DOWN;
                if (bL) dir = Dir.LEFT;
                if (bR) dir = Dir.RIGHT;
                if (bU) dir = Dir.UP;
                if (bD) dir = Dir.DOWN;
                tank.setDir(dir);
                tank.setMoving(true);
            }

        }
    }


}

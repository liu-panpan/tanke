package com.panpan.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Date 2021/7/18 18:33
 * @Author LiuPanpan
 * tank窗口类
 */
public class TankFrame extends Frame {
    static final int GAME_WIDTH = 1080;
    static final int GAME_HEIGHT = 960;
    GameModel gameModel = GameModel.getInstance();

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
        gameModel.paint(g);
//        System.out.println("paint");
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
                case KeyEvent.VK_S:
                    gameModel.save();
                    break;
                case KeyEvent.VK_L:
                    gameModel.load();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //键盘抬起来的事件
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
//                    gameModel.mainTank.fire();
                    gameModel.getMainTank().handleFireKey();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL&&!bR&&!bU&&!bD){
                gameModel.mainTank.setMoving(false);
            }else {
                Dir dir = Dir.DOWN;
                if (bL) dir = Dir.LEFT;
                if (bR) dir = Dir.RIGHT;
                if (bU) dir = Dir.UP;
                if (bD) dir = Dir.DOWN;
                gameModel.mainTank.setDir(dir);
                gameModel.mainTank.setMoving(true);
                new Thread(()->new Audio("audio/tank_move.wav").play()).start();
            }

        }
    }


}

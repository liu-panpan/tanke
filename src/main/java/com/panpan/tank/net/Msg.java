package com.panpan.tank.net;

/**
 * @Date 2021/8/10 22:05
 * @Author LiuPanpan
 */
public abstract class Msg {
    public abstract void handle();
    public abstract byte[] toBytes();
}
package com.panpan.tank.net;

import com.panpan.tank.Tank;
import com.panpan.tank.TankFrame;

import java.io.*;
import java.util.UUID;

public class TankStopMsg extends Msg {
    UUID id;
    int x,y;

    public TankStopMsg() {
    }

    public TankStopMsg(UUID id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public TankStopMsg(Tank tank) {
        this.id = tank.getId();
        this.x = tank.getX();
        this.y = tank.getY();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void handle() {
//if this msg is send by myself do nothing
        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getId()))
            return;

        Tank t = TankFrame.INSTANCE.findByUUID(this.id);

        if (t != null) {
            t.setMoving(false);
            t.setX(this.x);
            t.setY(this.y);
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream byteArrayOutputStream = null;
        DataOutputStream dataOutputStream = null;
        byte[] bytes = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeLong(id.getMostSignificantBits());
            dataOutputStream.writeLong(id.getLeastSignificantBits());
            dataOutputStream.writeInt(x);
            dataOutputStream.writeInt(y);
            dataOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (byteArrayOutputStream!=null){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dataOutputStream!=null){
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        try {
            this.id = new UUID(dataInputStream.readLong(),dataInputStream.readLong());
            this.x = dataInputStream.readInt();
            this.y = dataInputStream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStop;
    }

    @Override
    public String toString() {
        return "TankStopMsg{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

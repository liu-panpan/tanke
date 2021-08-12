package com.panpan.tank.net;

import com.panpan.tank.Dir;
import com.panpan.tank.Tank;
import com.panpan.tank.TankFrame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * @Date 2021/8/12 22:17
 * @Author LiuPanpan
 */
public class TankDirChangedMsg extends Msg {
    private UUID id;
    private Dir dir;

    private int x, y;

    public TankDirChangedMsg() {
    }

    public TankDirChangedMsg(Tank tank) {
        this.id = tank.getId();
        this.dir = tank.getDir();
        this.x = tank.getX();
        this.y = tank.getY();
    }

    public TankDirChangedMsg(UUID id, Dir dir, int x, int y) {
        this.id = id;
        this.dir = dir;
        this.x = x;
        this.y = y;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
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
        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getId()))
            return;

        Tank t = TankFrame.INSTANCE.findByUUID(this.id);

        if (t != null) {
            t.setMoving(true);
            t.setX(this.x);
            t.setY(this.y);
            t.setDir(this.dir);
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
            dataOutputStream.writeLong(this.id.getMostSignificantBits());
            dataOutputStream.writeLong(this.id.getLeastSignificantBits());
            dataOutputStream.writeInt(this.dir.ordinal());
            dataOutputStream.writeInt(this.x);
            dataOutputStream.writeInt(this.y);
            dataOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dataOutputStream != null) {
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
            this.dir = Dir.values()[dataInputStream.readInt()];
            this.x = dataInputStream.readInt();
            this.y = dataInputStream.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankDirChanged;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TankDirChangedMsg.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("dir=" + dir)
                .add("x=" + x)
                .add("y=" + y)
                .toString();
    }
}

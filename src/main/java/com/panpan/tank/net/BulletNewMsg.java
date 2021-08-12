package com.panpan.tank.net;

import com.panpan.tank.Bullet;
import com.panpan.tank.Dir;
import com.panpan.tank.Group;
import com.panpan.tank.TankFrame;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * @Date 2021/8/12 21:49
 * @Author LiuPanpan
 */
public class BulletNewMsg extends Msg {
    UUID playerId;
    UUID id;
    int x, y;
    Dir dir;
    Group group;

    public BulletNewMsg(Bullet bullet) {
        this.playerId = TankFrame.INSTANCE.getMainTank().getId();
        this.id = bullet.getId();
        this.x = bullet.getX();
        this.y = bullet.getY();
        this.dir = bullet.getDir();
        this.group = bullet.getGroup();
    }

    public BulletNewMsg() {
    }

    @Override
    public void handle() {
        if (this.playerId.equals(TankFrame.INSTANCE.getMainTank().getId()))
            return;

        Bullet bullet = new Bullet(x, y, dir, group, TankFrame.INSTANCE);
        bullet.setId(this.id);
        TankFrame.INSTANCE.addBullet(bullet);
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream byteArrayOutputStream = null;
        DataOutputStream dataOutputStream = null;
        byte[] bytes = null;

        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            //先写主站坦克的id
            dataOutputStream.writeLong(this.playerId.getMostSignificantBits());
            dataOutputStream.writeLong(this.playerId.getLeastSignificantBits());
            //写子弹id
            dataOutputStream.writeLong(this.id.getMostSignificantBits());
            dataOutputStream.writeLong(this.id.getLeastSignificantBits());

            dataOutputStream.writeInt(this.x);
            dataOutputStream.writeInt(this.y);
            dataOutputStream.writeInt(this.dir.ordinal());
            dataOutputStream.writeInt(this.group.ordinal());
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
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.playerId = new UUID(dataInputStream.readLong(), dataInputStream.readLong());
            this.id = new UUID(dataInputStream.readLong(), dataInputStream.readLong());
            this.x = dataInputStream.readInt();
            this.y = dataInputStream.readInt();
            this.dir = Dir.values()[dataInputStream.readInt()];
            this.group = Group.values()[dataInputStream.readInt()];
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.BulletNew;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BulletNewMsg.class.getSimpleName() + "[", "]")
                .add("playerId=" + playerId)
                .add("id=" + id)
                .add("x=" + x)
                .add("y=" + y)
                .add("dir=" + dir)
                .add("group=" + group)
                .toString();
    }
}

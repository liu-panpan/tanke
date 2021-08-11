package com.panpan.tank;

import com.panpan.tank.net.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Date 2021/8/7 15:44
 * @Author LiuPanpan
 */
public class TankStopMsgCodecTest {
    @Test
    void testEncoder() {
        EmbeddedChannel ch = new EmbeddedChannel();


        UUID id = UUID.randomUUID();
        TankStopMsg msg = new TankStopMsg(id,5, 10);
        ch.pipeline()
                .addLast(new MsgEncoder());

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)ch.readOutbound();
        MsgType msgType = MsgType.values()[buf.readInt()];
        assertEquals(MsgType.TankStop, msgType);

        int length = buf.readInt();
        assertEquals(24, length);

        UUID uuid = new UUID(buf.readLong(), buf.readLong());
        int x = buf.readInt();
        int y = buf.readInt();

        assertEquals(5, x);
        assertEquals(10, y);
        assertEquals(id, uuid);
    }

    @Test
    void testDecoder() {
        EmbeddedChannel ch = new EmbeddedChannel();


        UUID id = UUID.randomUUID();
        TankStopMsg msg = new TankStopMsg(id,5, 10);

        ch.pipeline()
                .addLast(new MsgDecoder());

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(MsgType.TankStop.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        ch.writeInbound(buf.duplicate());

        TankStopMsg msgR = ch.readInbound();
        assertEquals(id, msgR.getId());
        assertEquals(5, msgR.getX());
        assertEquals(10, msgR.getY());
    }
}

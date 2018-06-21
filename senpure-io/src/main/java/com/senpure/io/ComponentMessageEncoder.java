package com.senpure.io;

import com.senpure.io.message.Server2GatewayMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 罗中正 on 2018/2/28 0028.
 */
public class ComponentMessageEncoder extends MessageToByteEncoder<Server2GatewayMessage> {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    protected void encode(ChannelHandlerContext ctx, Server2GatewayMessage message, ByteBuf out) throws Exception {
        ByteBuf buf = Unpooled.buffer();
        message.getMessage().write(buf);
        int length = buf.writerIndex();
        //head 4 +messageId 4 token 4+ playerLen 2+playerIds*4+ content length
        int playerLen = message.getPlayerIds().length;
        int packageLen = 14 + (playerLen << 2) + length;
        out.ensureWritable(packageLen);
        out.writeInt(packageLen - 4);
        out.writeInt(message.getToken());
        out.writeShort(playerLen);
        for (int i : message.getPlayerIds()) {
            out.writeInt(i);
        }
        out.writeInt(message.getMessageId());
        out.writeBytes(buf);
    }


}

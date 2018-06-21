package com.senpure.io;

import com.senpure.io.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 罗中正 on 2017/4/6.
 */
public class MessageToByteBufEncoder extends MessageToByteEncoder<Message> {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    //包长int ,消息Id int, 二进制数据
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf out) throws Exception {


        ByteBuf buf = Unpooled.buffer();
        message.write(buf);
        int length = buf.writerIndex();
       // logger.debug("message length {}", length);
        //head 4 +messageId 4+ content length
        out.ensureWritable(8 + length);
        out.writeInt(length + 4);
        out.writeInt(message.getMessageId());
        out.writeBytes(buf);
       // logger.debug("out length {}", out.writerIndex());


    }
}

package com.senpure.io;


import com.senpure.base.util.Assert;
import com.senpure.io.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by 罗中正 on 2017/4/6.
 */
public class ByteBufToMessageDecoder extends ByteToMessageDecoder {
    protected Logger logger = LoggerFactory.getLogger(getClass());



    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int rl = in.readableBytes();
        if (rl < 4) {
            logger.debug("数据过短 s{}", rl);
            return;
        }
        in.markReaderIndex();
        int packageLength = in.readInt();
        if (packageLength == 0) {
            Assert.error("错误，数据包长度不能为0");
        }
        //logger.debug("pl={} ,rl={}", packageLength, in.readableBytes());
        //半包
        if (packageLength > in.readableBytes()) {
           if(packageLength>2000000)
           {
               ctx.close().sync();
           }
            logger.info("数据不够一个数据包 pl={} ,rl={}", packageLength, in.readableBytes());
            in.resetReaderIndex();
        } else {
            int messageId = in.readInt();
            Message message = MessageHandlerUtil.getEmptyMessage(messageId);
            int messageLengh = packageLength - 4;
            if (message == null) {
                logger.warn("没有找到消息处理程序 messageId {}", messageId);
                ctx.close();
                in.skipBytes(messageLengh);
            } else {
                try {
                    message.read(in.copy(in.readerIndex(), packageLength - 4));
                    out.add(message);
                } catch (Exception e) {
                    ctx.close();
                    logger.debug("二进制转换为消息失败 messageId {}, message{}", messageId, message);
                    logger.error("error",e);
                }
                in.skipBytes(messageLengh);

            }

        }


    }


}

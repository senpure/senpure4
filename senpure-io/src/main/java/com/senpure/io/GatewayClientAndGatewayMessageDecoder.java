package com.senpure.io;

import com.senpure.base.util.Assert;
import com.senpure.io.message.Client2GatewayMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by 罗中正 on 2018/2/28 0028.
 */
public class GatewayClientAndGatewayMessageDecoder extends ByteToMessageDecoder {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        int rl = in.readableBytes();
        if (rl < 4) {
            this.logger.debug("数据过短 s{}", Integer.valueOf(rl));
        } else {
            in.markReaderIndex();
            int packageLength = in.readInt();
            if (packageLength == 0) {
                Assert.error("错误，数据包长度不能为0");
            }
            if (packageLength > in.readableBytes()) {
                if (packageLength > 2000000) {
                    ctx.close().sync();
                }
                this.logger.info("数据不够一个数据包 packageLength ={} ,readableBytes={}", Integer.valueOf(packageLength), Integer.valueOf(in.readableBytes()));
                in.resetReaderIndex();
            } else {
                int messageId = in.readInt();
                int messageLength = packageLength - 4;
                byte data[] = new byte[messageLength];
                in.readBytes(data);
                Client2GatewayMessage transfer = new Client2GatewayMessage();
                transfer.setData(data);
                transfer.setMessageId(messageId);
                out.add(transfer);
            }

        }
    }
}

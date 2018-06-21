package com.senpure.io;

import com.senpure.base.util.Assert;
import com.senpure.io.message.Server2GatewayMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 将服务器转发到网关的消息，解析出来
 * Created by 罗中正 on 2018/2/28 0028.
 */
public class GatewayServerAndGatewayMessageDecoder extends ByteToMessageDecoder {
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
                int token=in.readInt();
                short playerLen = in.readShort();
               Integer[] playerIds = new Integer[playerLen];
                for (int i = 0; i <playerLen ; i++) {
                    playerIds[i] = in.readInt();
                }
                int messageId = in.readInt();
                int messageLength = packageLength - 10-(playerLen<<2);
                byte data[] = new byte[messageLength];
                in.readBytes(data);
                Server2GatewayMessage serverMessage=new Server2GatewayMessage();
                serverMessage.setData(data);
                serverMessage.setToken(token);
                serverMessage.setMessageId(messageId);
                serverMessage.setPlayerIds(playerIds);
                out.add(serverMessage);
            }

        }
    }

    public static void main(String[] args) {

        new ArrayList<>(17);
        System.out.println(2&8);
    }
}

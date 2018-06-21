package com.senpure.io;

import com.senpure.io.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by 罗中正 on 2017/5/27.
 */

public class ClientHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {


        MessageHandlerUtil.execute(ctx.channel(),message);
    }

}

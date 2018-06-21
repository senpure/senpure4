package com.senpure.io;


import com.senpure.io.message.Gateway2ServerMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by 罗中正 on 2018/2/28 0028.
 */
public class ComponentServerHandler extends SimpleChannelInboundHandler<Gateway2ServerMessage> {


    private ComponentMessageExecuter messageExecuter;

    public ComponentServerHandler(ComponentMessageExecuter messageExecuter) {
        this.messageExecuter = messageExecuter;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Gateway2ServerMessage msg) throws Exception {
        messageExecuter.execute(ctx.channel(),msg);
    }

}

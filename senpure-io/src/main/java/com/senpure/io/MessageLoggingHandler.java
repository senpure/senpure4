package com.senpure.io;

import com.senpure.io.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by 罗中正 on 2017/8/29.
 */
public class MessageLoggingHandler extends LoggingHandler {
    private IOMessageProperties config;

    public MessageLoggingHandler(LogLevel level, IOMessageProperties config) {
        super(level);
        this.config = config;
    }


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        if (this.logger.isEnabled(this.internalLevel)) {
            if (msg instanceof Message) {
                if (config.isOutFormat()) {
                    this.logger.log(this.internalLevel, "{} {} {}{}",
                            ChannelAttributeUtil.getChannelLogStr(ctx.channel()),
                            "WRITE", "\n", ((Message) msg).toString(null));
                    //this.logger.log(this.internalLevel, this.format(ctx, ChannelAttributeUtil.getChannelPlayerStr(ctx.channel())+" WRITE", "\n"+((Message) msg).toString(null)));
                } else {
                    this.logger.log(this.internalLevel, "{} {} {}",
                            ChannelAttributeUtil.getChannelLogStr(ctx.channel()),
                            "WRITE: ", msg);
                    //  this.logger.log(this.internalLevel, this.format(ctx, ChannelAttributeUtil.getChannelPlayerStr(ctx.channel())+" WRITE", msg));

                }

            } else {
                this.logger.log(this.internalLevel, "{} {} {}",
                        ChannelAttributeUtil.getChannelLogStr(ctx.channel()),
                        "WRITE: ", msg);
            }
        }

        ctx.write(msg, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (this.logger.isEnabled(this.internalLevel)) {
            if (msg instanceof Message) {
                if (config.isInFormat()) {
                    this.logger.log(this.internalLevel, "{} {} {}{}",
                            ChannelAttributeUtil.getChannelLogStr(ctx.channel()),
                            "RECEIVED", "\n", ((Message) msg).toString(null));
                   // this.logger.log(this.internalLevel, this.format(ctx, ChannelAttributeUtil.getChannelPlayerStr(ctx.channel()) + " RECEIVED", "\n" + ((Message) msg).toString(null)));

                } else {
                    this.logger.log(this.internalLevel, "{} {} {}{}",
                            ChannelAttributeUtil.getChannelLogStr(ctx.channel()),
                            "RECEIVED: ",  msg);
                }
            } else {
                this.logger.log(this.internalLevel, "{} {} {}{}",
                        ChannelAttributeUtil.getChannelLogStr(ctx.channel()),
                        "RECEIVED: ",  msg);
            }
        }
        ctx.fireChannelRead(msg);

    }
}

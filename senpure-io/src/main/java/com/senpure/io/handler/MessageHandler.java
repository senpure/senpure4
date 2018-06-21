package com.senpure.io.handler;

import com.senpure.io.message.Message;
import io.netty.channel.Channel;


/**
 * Created by 罗中正 on 2017/5/27.
 */
public interface MessageHandler<T extends Message> {


    T getEmptyMessage();
    void execute(Channel channel, T message);
    int handlerId();


}

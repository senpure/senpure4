package com.senpure.io.handler;

import com.senpure.io.message.Message;
import io.netty.channel.Channel;

/**
 * Created by 罗中正 on 2017/5/27.
 */
public interface ComponentMessageHandler<T extends Message> {


    T getEmptyMessage();

    void execute(Channel channel, int token, int playerId, T message);

    int handlerId();

    /**
     * 消息类型 0 可以直接转发过来 1 网关读取范围  2 网关询问
     */
    int messageType();


    /**
     * true 表示不同的服务器
     */
    boolean serverShare();


    /**
     * 类型 0int 1 long 2 String
     */

    int valueType();

    long numStart();

    long numEnd();

    boolean regToGateway();

}

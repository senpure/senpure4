package com.senpure.io.handler;

import com.senpure.io.ComponentGatewayServer;
import com.senpure.io.message.CSAskHandleMessage;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 询问服务器是否可以处理该值得请求处理器
 *
 * @author senpure-generator
 * @version 2018-3-21 16:38:05
 */

public class CSAskHandleMessageHandler extends AbstractInnerComponentMessageHandler<CSAskHandleMessage> {
    @Autowired
    private ComponentGatewayServer gatewayServer;

    @Override
    public void execute(Channel channel, int token, int playerId, CSAskHandleMessage message) {

        gatewayServer.canHandleMessageValue(message.getFromMessageId(), message.getValue());
    }


    @Override
    public int handlerId() {
        return 1105;
    }

    @Override
    public CSAskHandleMessage getEmptyMessage() {
        return new CSAskHandleMessage();
    }


}
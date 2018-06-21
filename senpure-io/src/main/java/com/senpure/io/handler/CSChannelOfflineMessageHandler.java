package com.senpure.io.handler;

import com.senpure.io.ComponentGatewayServer;
import com.senpure.io.message.CSChannelOfflineMessage;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 客户端掉线处理器
 *
 * @author senpure-generator
 * @version 2018-3-16 17:14:32
 */
public class CSChannelOfflineMessageHandler extends AbstractInnerComponentMessageHandler<CSChannelOfflineMessage> {

    @Autowired
    private ComponentGatewayServer gatewayServer;
    @Override
    public void execute(Channel channel, int token, int playerId, CSChannelOfflineMessage message) {

        gatewayServer.offline(message.getToken(),message.getPlayerId());
    }

    public void setGatewayServer(ComponentGatewayServer gatewayServer) {
        this.gatewayServer = gatewayServer;
    }

    @Override
    public int handlerId() {
        return 1103;
    }

    @Override
    public CSChannelOfflineMessage getEmptyMessage() {
        return new CSChannelOfflineMessage();
    }
}
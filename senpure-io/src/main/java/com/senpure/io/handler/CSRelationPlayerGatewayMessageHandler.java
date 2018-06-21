package com.senpure.io.handler;

import com.senpure.io.ChannelAttributeUtil;
import com.senpure.io.ComponentGatewayServer;
import com.senpure.io.message.CSRelationPlayerGatewayMessage;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 关联玩家与网关处理器
 *
 * @author senpure-generator
 * @version 2018-3-16 17:14:32
 */
public class CSRelationPlayerGatewayMessageHandler extends AbstractInnerComponentMessageHandler<CSRelationPlayerGatewayMessage> {

    @Autowired
    private ComponentGatewayServer gatewayServer;
    @Override
    public void execute(Channel channel, int token, int playerId, CSRelationPlayerGatewayMessage relationMessage) {
        String gatewayKey = ChannelAttributeUtil.getIpAndPort(channel);
        if (relationMessage.getPlayerId() > 0) {
            gatewayServer.relationPlayer(gatewayKey, relationMessage.getPlayerId());
        }
        if (relationMessage.getToken() != 0) {
            gatewayServer.relationToken(gatewayKey, relationMessage.getToken());
        }
    }

    public void setGatewayServer(ComponentGatewayServer gatewayServer) {
        this.gatewayServer = gatewayServer;
    }
    @Override
    public int handlerId() {
        return 1101;
    }

    @Override
    public CSRelationPlayerGatewayMessage getEmptyMessage() {
        return new CSRelationPlayerGatewayMessage();
    }
}
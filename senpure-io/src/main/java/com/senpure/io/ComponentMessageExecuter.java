package com.senpure.io;

import com.senpure.io.handler.ComponentMessageHandler;
import com.senpure.io.message.*;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by 罗中正 on 2018/3/1 0001.
 */
public class ComponentMessageExecuter {

    private ExecutorService service;
    private ComponentGatewayServer gatewayServer;
    private CSRelationPlayerGatewayMessage message = new CSRelationPlayerGatewayMessage();

    private int relationMessageId = message.getMessageId();


    public ComponentMessageExecuter(ComponentGatewayServer gatewayServer) {
        this.gatewayServer = gatewayServer;
        service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }

    public ComponentMessageExecuter(ExecutorService service, ComponentGatewayServer gatewayServer) {
        this.service = service;
        this.gatewayServer = gatewayServer;
    }

    private Logger logger = LoggerFactory.getLogger(ComponentMessageExecuter.class);

    public void execute(Runnable runnable) {
        service.execute(runnable);
    }

    public void execute(Channel channel, Gateway2ServerMessage gsMessage) {
        service.execute(() -> {
            if (gsMessage.getMessageId() == relationMessageId) {
                CSRelationPlayerGatewayMessage relationMessage = new CSRelationPlayerGatewayMessage();
                relationMessage.read(gsMessage.getBuf());

                logger.debug(relationMessage.toString());
                String gatewayKey = ChannelAttributeUtil.getIpAndPort(channel);
                logger.debug("gatewayKey :{}", gatewayKey);
                if (relationMessage.getPlayerId() > 0) {
                    gatewayServer.relationPlayer(gatewayKey, relationMessage.getPlayerId());
                }
                if (relationMessage.getToken() != 0) {
                    gatewayServer.relationToken(gatewayKey, relationMessage.getToken());
                }
                return;
            }
            int playerId = gsMessage.getPlayerId();
            ComponentMessageHandler handler = ComponentMessageHandlerUtil.getHandler(gsMessage.getMessageId());
            if (handler == null) {
                logger.warn("没有找到消息出来程序{} playerId:{}", gsMessage.getMessageId(), playerId);
                return;
            }
            Message message = handler.getEmptyMessage();
            message.read(gsMessage.getBuf());
            handler.execute(channel, gsMessage.getToken(), playerId, message);
            //  ComponentMessageHandlerUtil.execute(gsMessage.getToken(),playerId,message);

        });
    }


}

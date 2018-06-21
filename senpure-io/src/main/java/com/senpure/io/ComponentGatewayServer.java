package com.senpure.io;

import com.senpure.io.message.Message;
import com.senpure.io.message.Server2GatewayMessage;
import io.netty.channel.Channel;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by 罗中正 on 2018/3/5 0005.
 */
public class ComponentGatewayServer {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    private ConcurrentMap<String, ComponentGatewayChannelServer> gatewayChannelMap = new ConcurrentHashMap<>();

    private ConcurrentMap<Integer, ComponentGatewayChannelServer> playerGatewayMap = new ConcurrentHashMap<>();

    private ConcurrentMap<Integer, ComponentGatewayChannelServer> tokenGatewayMap = new ConcurrentHashMap<>();

    public synchronized ComponentGatewayChannelServer getComponentGatewayChannelServer(String serverKey) {

        ComponentGatewayChannelServer server = gatewayChannelMap.get(serverKey);
        if (server == null) {
            server = new ComponentGatewayChannelServer();
            gatewayChannelMap.put(serverKey, server);
            return gatewayChannelMap.get(serverKey);
        }
        return server;
    }

    public void relationPlayer(String serverKey, Integer playerId) {
        playerGatewayMap.putIfAbsent(playerId, gatewayChannelMap.get(serverKey));
    }

    public void relationToken(String serverKey, Integer token) {
        tokenGatewayMap.putIfAbsent(token, gatewayChannelMap.get(serverKey));
    }

    public void breakPlayer(Integer playerId) {
        playerGatewayMap.remove(playerId);
    }

    public void breakToken(Integer token) {
        tokenGatewayMap.remove(token);
    }

    public void offline(int token, int playerId) {
        logger.debug("token {} id {}离线", token, playerId);
        if (token != 0) {
            breakToken(token);
        }
        if (playerId > 0) {
            breakPlayer(playerId);
        }
    }

    public boolean canHandleMessageValue(int messageId,String value)
    {
        return  false;
    }

    public void sendMessage2GatewayByToken(Integer token, Message message) {
        Server2GatewayMessage toGateway = new Server2GatewayMessage();
        toGateway.setToken(token);
        toGateway.setPlayerIds(new Integer[0]);
        toGateway.setMessage(message);
        toGateway.setMessageId(message.getMessageId());
        ComponentGatewayChannelServer channelServer = tokenGatewayMap.get(token);
        Channel channel = channelServer.nextChannel();
        channel.writeAndFlush(toGateway);

    }

    public void sendMessage2Gateway(Integer playerId, Message message) {
        Server2GatewayMessage toGateway = new Server2GatewayMessage();
        toGateway.setPlayerIds(new Integer[]{playerId});

        toGateway.setMessage(message);
        toGateway.setMessageId(message.getMessageId());
        playerGatewayMap.get(playerId).nextChannel().writeAndFlush(toGateway);

    }

    public void sendMessage2Gateway(List<Integer> playerIds, Message message) {
        Map<Integer, GatewayPlayers> map = new HashMap<>();
        for (Integer playerId : playerIds) {
            ComponentGatewayChannelServer gatewayChannelServer = playerGatewayMap.get(playerId);
            Integer number = gatewayChannelServer.getNumber();
            GatewayPlayers gatewayPlayers = map.get(number);
            if (gatewayPlayers == null) {
                gatewayPlayers = new GatewayPlayers();
                gatewayPlayers.gatewayChannelServer = gatewayChannelServer;
                map.put(number, gatewayPlayers);
            }
            gatewayPlayers.playerIds.add(playerId);
        }
        map.values().forEach(gatewayPlayers -> {
            Server2GatewayMessage toGateway = new Server2GatewayMessage();
            toGateway.setMessage(message);
            toGateway.setMessageId(message.getMessageId());
            Integer[] players = new Integer[gatewayPlayers.playerIds.size()];

            gatewayPlayers.playerIds.toArray(players);

            gatewayPlayers.gatewayChannelServer.nextChannel().writeAndFlush(toGateway);

        });

    }

    class GatewayPlayers {
        List<Integer> playerIds = new ArrayList<>(16);
        ComponentGatewayChannelServer gatewayChannelServer;
    }
}

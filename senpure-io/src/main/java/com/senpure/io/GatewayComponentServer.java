package com.senpure.io;

import com.senpure.io.message.CSRelationPlayerGatewayMessage;
import com.senpure.io.message.Client2GatewayMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 网关管理一个服务的多个实例 每个实例可能含有多个管道channel
 * Created by 罗中正 on 2018/3/5 0005.
 */
public class GatewayComponentServer {
    private CSRelationPlayerGatewayMessage message = new CSRelationPlayerGatewayMessage();
    private int relationMessageId = message.getMessageId();
    private ConcurrentMap<Integer, Channel> playerChannelMap = new ConcurrentHashMap<>();
    private List<GatewayComponentChannelServer> useChannelServer = new ArrayList<>();

    private List<GatewayComponentChannelServer> prepStopOldInstance = new ArrayList<>();
    private Map<Integer, Boolean> handleIdsMap = new HashMap<>();
    private String serverName;
    private AtomicInteger atomicIndex = new AtomicInteger(-1);

    public Channel channel(Integer playerId, int token) {
        Channel channel = playerChannelMap.get(playerId);
        if (channel == null) {
            channel = nextGatewayComponentChannelServer().nextChannel();
            relationGateway(channel, token, playerId);
            return channel;
        }
        return channel;
    }

    public void relationGateway(Channel channel, int token, int playerId) {
        if (playerId > 0) {
            playerChannelMap.put(playerId, channel);
        }
        CSRelationPlayerGatewayMessage message = new CSRelationPlayerGatewayMessage();
        message.setPlayerId(playerId);
        message.setToken(token);
        Client2GatewayMessage toMessage = new Client2GatewayMessage();
        toMessage.setMessageId(relationMessageId);
        ByteBuf buf = Unpooled.buffer();
        message.write(buf);
        toMessage.setData(buf.array());
        channel.writeAndFlush(toMessage);
    }

    private GatewayComponentChannelServer nextGatewayComponentChannelServer() {
        GatewayComponentChannelServer server = useChannelServer.get(nextIndex());
        return server;
    }

    private int nextIndex() {
        if (useChannelServer.size() == 0) {
            return 0;
        }
        int index = atomicIndex.incrementAndGet();
        if (index >= useChannelServer.size()) {
            boolean reset = atomicIndex.compareAndSet(index, 0);
            if (!reset) {
                return nextIndex();
            }
            return 0;
        }
        return index;
    }

    public synchronized void prepStopOldInstance() {
        prepStopOldInstance.addAll(useChannelServer);
        useChannelServer.clear();
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }


    public GatewayComponentChannelServer getChannelServer(String serverKey) {
        for (GatewayComponentChannelServer server : useChannelServer) {
            if (server.getServerKey().equals(serverKey)) {
                return server;
            }
        }
        GatewayComponentChannelServer server = new GatewayComponentChannelServer();
        server.setServerKey(serverKey);
        return server;

    }

    public List<GatewayComponentChannelServer> getUseChannelServer() {
        return useChannelServer;
    }

    public synchronized void checkChannelServer(String serverKey, GatewayComponentChannelServer channelServer) {
        for (GatewayComponentChannelServer server : useChannelServer) {
            if (server.getServerKey().equals(serverKey)) {
                return;
            }
        }
        useChannelServer.add(channelServer);
    }

    public void markHandleId(int messageId) {
        handleIdsMap.put(messageId, true);
    }

    public boolean handleId(int messageId) {
        return handleIdsMap.get(messageId) != null;
    }

    public static void main(String[] args) {

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        Integer key = 1;
        String s = map.putIfAbsent(key, "2");
        System.out.println(map.get(key));
        System.out.println(s);
        s = map.putIfAbsent(key, "3");
        System.out.println(map.get(key));
        System.out.println(s);
    }
}

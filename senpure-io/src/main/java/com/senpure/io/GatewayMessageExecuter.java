package com.senpure.io;

import com.senpure.io.bean.HandleMessage;
import com.senpure.io.message.*;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by 罗中正 on 2018/3/1 0001.
 */

public class GatewayMessageExecuter {
    private static Logger logger = LoggerFactory.getLogger(GatewayMessageExecuter.class);
    private ExecutorService service;
    private int csLoginMessageId = 1;
    private int csLogoutMessageId = 2;

    private int scLoginMessageId = 3;
    private int scLogoutMessageId = 4;
    private Message regServerInstanceMessage = new SCRegServerHandleMessageMessage();
    private int regServerInstanceMessageId = regServerInstanceMessage.getMessageId();
    private Message askMessage = new SCAskHandleMessage();
    private int askMessageId = askMessage.getMessageId();

    private ConcurrentMap<Integer, Channel> prepLoginChannels = new ConcurrentHashMap<>(2048);

    private ConcurrentMap<Integer, Channel> playerClientChannel = new ConcurrentHashMap<>(32768);
    private ConcurrentMap<Integer, Channel> tokenChannel = new ConcurrentHashMap<>(32768);
    private ConcurrentMap<String, GatewayComponentServer> serverInstanceMap = new ConcurrentHashMap<>(128);

    private ConcurrentMap<Integer, GatewayComponentServer> messageHandleMap = new ConcurrentHashMap<>(2048);
    private ConcurrentMap<Integer, GatewayHandleMessageServer> handleMessageMap = new ConcurrentHashMap<>(2048);

    private ConcurrentMap<Long, AskMessage> askMap = new ConcurrentHashMap<>();

    public GatewayMessageExecuter() {
        service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }

    public GatewayMessageExecuter(ExecutorService service) {
        this.service = service;
    }

    public void channelActive(Channel channel) {
        tokenChannel.putIfAbsent(channel.hashCode(), channel);
    }

    //将客户端消息转发给具体的服务器
    public void execute(final Channel channel, final Client2GatewayMessage message) {
        service.execute(() -> {
            logger.info("messageId {} data {}", message.getMessageId(), message.getData()[0]);
            //登录
            if (message.getMessageId() == csLoginMessageId) {
                prepLoginChannels.putIfAbsent(channel.hashCode(), channel);

            }
            message.setToken(channel.hashCode());
            //转发到具体的子服务器
            Integer playerId = ChannelAttributeUtil.getPlayerId(channel);
            if (playerId != null) {
                message.setPlayerId(playerId);
            }
            Channel componentChannel = getChannel(message);
            if (componentChannel == null) {
                logger.info("没有找到消息的接收服务器{}", message.getMessageId());
                return;
            }
            componentChannel.writeAndFlush(message);

        });
    }

    public Channel getChannel(Client2GatewayMessage message) {
        GatewayComponentServer componentServer = messageHandleMap.get(message.getMessageId());
        if (componentServer != null) {
            return componentServer.channel(message.getPlayerId(), message.getToken());
        } else {
            logger.warn("没有找到处理[{}] 的服务器", message.getMessageId());
            logger.info("{}", messageHandleMap);
            logger.info("{}", serverInstanceMap);
        }
        return null;
    }

    //处理服务器发过来的消息
    public void execute(Channel channel, final Server2GatewayMessage message) {
        if (message.getMessageId() == regServerInstanceMessageId) {
            regServerInstance(channel, message);
            return;
        } else if (message.getMessageId() == askMessageId) {
            askMessage(channel, message);
            return;
        }
        if (message.getMessageId() == scLoginMessageId) {
            int playerId = message.getPlayerIds()[0];
            Channel clientChannel = prepLoginChannels.remove(message.getToken());
            if (clientChannel != null) {
                ChannelAttributeUtil.setPlayerId(clientChannel, playerId);
                playerClientChannel.putIfAbsent(playerId, clientChannel);
            }
        }

        if (message.getPlayerIds().length == 0) {
            Channel clientChannel = tokenChannel.get(message.getToken());
            if (clientChannel == null) {
                logger.warn("没有找到channel{}", message.getToken());
            } else {
                clientChannel.writeAndFlush(message);
            }
        } else {
            for (Integer playerId : message.getPlayerIds()) {
                Channel clientChannel = playerClientChannel.get(playerId);
                if (clientChannel == null) {
                    logger.warn("没有找到玩家{}", playerId);
                } else {
                    clientChannel.writeAndFlush(message);
                }
            }
        }

    }

    public void askMessage(Channel channel, Server2GatewayMessage server2GatewayMessage) {
        SCAskHandleMessage message = new SCAskHandleMessage();
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(server2GatewayMessage.getData());
        message.read(buf);
        if (message.isHandle()) {

        }
    }

    public synchronized void regServerInstance(Channel channel, Server2GatewayMessage server2GatewayMessage) {
        SCRegServerHandleMessageMessage message = new SCRegServerHandleMessageMessage();
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(server2GatewayMessage.getData());
        message.read(buf);
        List<HandleMessage> handleMessages = message.getMessages();
        String serverKey = message.getServerName() + message.getIpAndFirstPort();
        logger.info("服务注册:{}:{} [{}]", message.getServerName(), message.getIpAndFirstPort(), message.getReadableServerName());
        for (HandleMessage handleMessage : handleMessages) {
            logger.info("{}", handleMessage);
        }
        GatewayComponentServer componentServer = serverInstanceMap.get(message.getServerName());
        if (componentServer == null) {
            componentServer = new GatewayComponentServer();
            serverInstanceMap.put(message.getServerName(), componentServer);
            for (HandleMessage handleMessage : handleMessages) {
                componentServer.markHandleId(handleMessage.getHandleMessageId());
                messageHandleMap.putIfAbsent(handleMessage.getHandleMessageId(), componentServer);
            }
            componentServer.setServerName(message.getServerName());
        }

        for (HandleMessage handleMessage : handleMessages) {
            GatewayHandleMessageServer handleMessageServer = null;
            if (handleMessage.isServerShare()) {
                handleMessageServer = handleMessageMap.get(handleMessage.getHandleMessageId());
                if (handleMessage == null) {
                    List<GatewayComponentServer> gatewayComponentServers = new ArrayList<>();
                    //  handleMessageServer = new GatewayHandleMessageServer(gatewayComponentServers);
                }

            } else {
                // handleMessageServer = new GatewayHandleMessageServer(componentServer);
            }
            handleMessageServer.setNumStart(handleMessage.getNumStart());
            handleMessageServer.setNumEnd(handleMessage.getNumEnd());
            handleMessageServer.setMessageType(handleMessage.getMessageType());
            handleMessageServer.setValueType(handleMessage.getValueType());
        }
        //如果同一个服务有新的消息处理，旧得实例停止接收新的连接
        for (HandleMessage handleMessage : handleMessages) {
            if (!componentServer.handleId(handleMessage.getHandleMessageId())) {
                logger.info("{} 处理了新的消息{}[{}] ，旧的服务器停止接收新的请求分发", message.getServerName(), handleMessage.getHandleMessageId(), handleMessage.getMessageClasses());
                componentServer.prepStopOldInstance();
                for (HandleMessage hm : handleMessages) {
                    componentServer.markHandleId(hm.getHandleMessageId());
                }
                break;
            }
        }
        GatewayComponentChannelServer componentChannelServer = componentServer.getChannelServer(serverKey);
        componentChannelServer.addChannel(channel);
        componentServer.checkChannelServer(serverKey, componentChannelServer);

    }

    public void addAskMessage(AskMessage askMessage) {
        askMap.put(askMessage.getToken(), askMessage);
    }

}

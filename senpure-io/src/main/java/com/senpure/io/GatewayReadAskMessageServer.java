package com.senpure.io;

import com.senpure.io.message.AskMessage;
import com.senpure.io.message.CSAskHandleMessage;
import com.senpure.io.message.Client2GatewayMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by 罗中正 on 2018/3/19 0019.
 */
public class GatewayReadAskMessageServer extends GatewayHandleMessageServer {

    private List<GatewayComponentServer> gatewayComponentServers = new ArrayList<>(16);
    private AtomicLong atomicToken = new AtomicLong(1);

    private GatewayMessageExecuter messageExecuter;
    public GatewayReadAskMessageServer(boolean serverShare, int messageType) {
        super(messageType);
        setServerShare(serverShare);
    }

    public void handleMessage(Client2GatewayMessage message) {
        if (getMessageType() == 1) {
            read(message);
        } else if (getMessageType() == 2) {
            ask(message);
        }
    }

    public void ask(Client2GatewayMessage message) {

        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(message.getData());
        int length = buf.readInt();
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        try {
            String value = new String(bytes, "utf-8");
            CSAskHandleMessage askMessage = new CSAskHandleMessage();
            askMessage.setFromMessageId(message.getMessageId());
            askMessage.setValue(value);
            AskMessage ask = new AskMessage();
            ask.setAskTime(System.currentTimeMillis());
            ask.setMessage(message);
            ask.setToken(nextToken());
            for (GatewayComponentServer gatewayComponentServer : gatewayComponentServers) {
                List<GatewayComponentChannelServer> componentChannelServers = gatewayComponentServer.getUseChannelServer();
                for (GatewayComponentChannelServer componentChannelServer : componentChannelServers) {
                    Channel channel = componentChannelServer.nextChannel();
                    gatewayComponentServer.relationGateway(channel, message.getToken(), message.getPlayerId());
                    channel.writeAndFlush(askMessage);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void read(Client2GatewayMessage message) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(message.getData());
        long value = 0;
        if (getValueType() == 0) {
            value = buf.readInt();
        } else {
            value = buf.readLong();
        }
        for (GatewayComponentServer gatewayComponentServer : gatewayComponentServers) {
            List<GatewayComponentChannelServer> componentChannelServers = gatewayComponentServer.getUseChannelServer();
            for (GatewayComponentChannelServer componentChannelServer : componentChannelServers) {
                if (componentChannelServer.handle(value)) {
                    Channel channel = componentChannelServer.nextChannel();
                    gatewayComponentServer.relationGateway(channel, message.getToken(), message.getPlayerId());
                    channel.writeAndFlush(message);
                    break;
                }
            }
        }

    }

    long tokenMax = Long.MAX_VALUE - 5000000;

    public long nextToken() {
        long token = atomicToken.incrementAndGet();
        if (token >= tokenMax) {
            boolean reset = atomicToken.compareAndSet(token, 1);
            if (!reset) {
                return nextToken();
            }
        }
        return token;
    }
}

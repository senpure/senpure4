package com.senpure.io;

import com.senpure.io.message.Client2GatewayMessage;

/**
 * Created by 罗中正 on 2018/3/19 0019.
 */
public class GatewayForwardMessageServer extends GatewayHandleMessageServer {

    private GatewayComponentServer gatewayComponentServer;


    public GatewayForwardMessageServer(GatewayComponentServer gatewayComponentServer) {
        super(0);
        this.gatewayComponentServer = gatewayComponentServer;
    }

    public void handleMessage(Client2GatewayMessage message) {

        gatewayComponentServer.channel(message.getPlayerId(), message.getToken()).writeAndFlush(message);
    }
}

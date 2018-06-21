package com.senpure.io;

import com.senpure.base.util.Assert;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 罗中正 on 2018/3/1 0001.
 */
public class ComponentServer {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    private IOServerProperties properties;
    private IOMessageProperties ioMessageProperties;
    private ChannelFuture channelFuture;
    private EventLoopGroup group;
    private String serverName = "gameServer";
    private String readableServerName = "gameServer";
    private ComponentMessageExecuter messageExecuter;

    private static int firstPort = 0;

    private static Map<String, Integer> firstPortMap = new ConcurrentHashMap<>();

    private String host;
    private Channel channel;
    private ComponentGatewayServer gatewayServer;


    public boolean start(String host, int port) throws CertificateException, SSLException {
        Assert.notNull(gatewayServer);
        Assert.notNull(messageExecuter);
        this.host = host;
        if (properties == null) {
            properties = new IOServerProperties();
        }
        if (ioMessageProperties == null) {
            ioMessageProperties = new IOMessageProperties();
            ioMessageProperties.setInFormat(properties.isInFormat());
            ioMessageProperties.setOutFormat(properties.isOutFormat());
        }

        logger.debug("启动{}，网关地址 {}", getReadableServerName(), host + ":" + port);
        readableServerName = readableServerName + "[" + host + ":" + port + "]";
        // Configure SSL.
        final SslContext sslCtx;
        if (properties.isSsl()) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        if (sslCtx != null) {
                            p.addLast(sslCtx.newHandler(ch.alloc(), host, port));
                        }
                        p.addLast(new ComponentMessageDecoder());
                        p.addLast(new ComponentMessageEncoder());
                        p.addLast(new MessageLoggingHandler(LogLevel.DEBUG, ioMessageProperties));
                        p.addLast(new ComponentServerHandler(messageExecuter));
                    }
                });

        // Start the client.
        try {

            channelFuture = b.connect(host, port).sync();
            channel = channelFuture.channel();
            InetSocketAddress address = (InetSocketAddress) channel.localAddress();
            logger.info("{}启动完成", getReadableServerName());
            markFirstPort(host, address.getPort());
            String gatewayKey=host+":"+port;
            ComponentGatewayChannelServer channelServer = gatewayServer.getComponentGatewayChannelServer( gatewayKey);
            channelServer.addChannel(channel);
            ChannelAttributeUtil.setIpAndPort(channel,gatewayKey);

        } catch (Exception e) {
            logger.error("启动" + getReadableServerName() + " 失败", e);
            destroy();
            return false;
        }
        return true;

    }



    public Channel getChannel() {
        return channel;
    }

    public String getReadableServerName() {
        return readableServerName;
    }

    public void destroy() {
        if (channelFuture != null) {
            channelFuture.channel().close();
        }
        if (group != null) {
            group.shutdownGracefully();
        }

        logger.debug("关闭{}并释放资源 ", getReadableServerName());

    }

    private synchronized static void markFirstPort(String host, int port) {

        firstPortMap.putIfAbsent(host, port);

    }

    public void setGatewayServer(ComponentGatewayServer gatewayServer) {
        this.gatewayServer = gatewayServer;
    }

    public int getFirstPort() {
        return firstPortMap.get(host);
    }

    public String getServerName() {
        return serverName;
    }


    public void setMessageExecuter(ComponentMessageExecuter messageExecuter) {
        this.messageExecuter = messageExecuter;
    }

    public IOServerProperties getProperties() {
        return properties;
    }

    public void setProperties(IOServerProperties properties) {
        this.properties = properties;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }


    public void setReadableServerName(String readableServerName) {
        this.readableServerName = readableServerName;
    }
}

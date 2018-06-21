package com.senpure.io;

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
import java.security.cert.CertificateException;

/**
 * Created by 罗中正 on 2018/3/1 0001.
 */
public class ClientServer {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    private IOServerProperties properties;
    private IOMessageProperties ioMessageProperties;
    private ChannelFuture channelFuture;
    private EventLoopGroup group;
    private String serverName = "游戏客户端";
    private Channel channel;

    public boolean start(String host, int port) throws CertificateException, SSLException {
        if (properties == null) {
            properties = new IOServerProperties();
        }
        if (ioMessageProperties == null) {
            ioMessageProperties = new IOMessageProperties();
            ioMessageProperties.setInFormat(properties.isInFormat());
            ioMessageProperties.setOutFormat(properties.isOutFormat());
        }

        logger.debug("启动{}，服务器地址 {}", getServerName(), host + ":" + port);
        serverName = serverName + "[" + host + ":" + port + "]";
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
                        p.addLast(new ByteBufToMessageDecoder());
                        p.addLast(new MessageToByteBufEncoder());
                        OffLineHandler offLineHandler = new OffLineHandler();
                        ChannelAttributeUtil.setOfflineHandler(ch, offLineHandler);
                        p.addLast(offLineHandler);
                        p.addLast(new MessageLoggingHandler(LogLevel.DEBUG, ioMessageProperties));
                        p.addLast(new ClientHandler());
                    }
                });

        // Start the client.
        try {
            logger.info("{}启动完成", getServerName());
            channelFuture = b.connect(host, port).sync();
            channel=channelFuture.channel();
        } catch (Exception e) {
            logger.error("启动" + getServerName() + " 失败", e);
            destroy();
            return false;
        }
        return true;

    }
    public void destroy() {
        if (channelFuture != null) {
            channelFuture.channel().close();
        }
        if (group != null) {
            group.shutdownGracefully();
        }

        logger.debug("关闭{}并释放资源 ", getServerName());

    }

    public Channel getChannel() {
        return channel;
    }

    public String getServerName() {
        return serverName;
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
}

package com.senpure.io;

import com.senpure.base.util.Assert;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
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

public class GatewayServerAndGatewayServer {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private IOServerProperties properties;
    private IOMessageProperties ioMessageProperties;
    private ChannelFuture channelFuture;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private String serverName = "gatewayServer[sc]";
    private String readableServerName = "网关[SC]服务器";

    private GatewayMessageExecuter messageExecuter;

    public void start() throws CertificateException, SSLException {
        Assert.notNull(messageExecuter);
        if (properties == null) {
            properties = new IOServerProperties();
        }
        if (ioMessageProperties == null) {
            ioMessageProperties = new IOMessageProperties();
            ioMessageProperties.setInFormat(properties.isScInFormat());
            ioMessageProperties.setOutFormat(properties.isScOutFormat());
        }
        logger.debug("启动{}，监听端口号 {}", getReadableServerName(), properties.getScPort());
        readableServerName = readableServerName + "[" + properties.getScPort() + "]";
        final SslContext sslCtx;
        // Configure SSL.
        if (properties.isSsl()) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }
        // Configure the server.
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc()));
                            }
                            p.addLast(new GatewayServerAndGatewayMessageDecoder());
                            p.addLast(new GatewayServerAndGatewayMessageEncoder());
                            p.addLast(new MessageLoggingHandler(LogLevel.DEBUG, ioMessageProperties));
                            OffLineHandler offLineHandler = new OffLineHandler();
                            ChannelAttributeUtil.setOfflineHandler(ch, offLineHandler);
                            p.addLast(offLineHandler);
                            p.addLast(new GatewayServerAndGatewayServerHandler(messageExecuter));

                        }
                    });
            // Start the server.
            channelFuture = b.bind(properties.getScPort()).sync();
            logger.info("{}启动完成", getReadableServerName());
        } catch (Exception e) {
            logger.error("启动" + getReadableServerName() + " 失败", e);
            destroy();
        }


    }


    public void destroy() {
        if (channelFuture != null) {
            channelFuture.channel().close();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        logger.info("关闭{}并释放资源 ", getReadableServerName());

    }

    public String getReadableServerName() {
        return readableServerName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setMessageExecuter(GatewayMessageExecuter messageExecuter) {
        this.messageExecuter = messageExecuter;
    }

    public void setProperties(IOServerProperties properties) {
        this.properties = properties;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}

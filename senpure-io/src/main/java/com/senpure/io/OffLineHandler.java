package com.senpure.io;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理网络断开(正常断开，与非正常断开)
 *
 * @author 罗中正
 * @version 1.0
 */
public class OffLineHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(OffLineHandler.class);

    public static final String OFF_LINE_HANDLER_NAME = OffLineHandler.class.getName();
    private List<OffLineListener> offLines = new ArrayList<>();

    private static List<OffLineListener> commonOffLines = new ArrayList<>();

    public static void regChannelOffLineListener(Channel channel, OffLineListener listener) {

        logger.debug("{} 注册channel掉线处理程序:", ChannelAttributeUtil.getChannelLogStr(channel), listener.getOffLineListenerName());

        ChannelAttributeUtil.getOfflineHandler(channel).offLines.add(listener);

    }


    public static void removeChannelOffLineListener(Channel channel, OffLineListener listener) {

        logger.debug("{} 移除channel掉线处理程序:", ChannelAttributeUtil.getChannelLogStr(channel), listener.getOffLineListenerName());

        ChannelAttributeUtil.getOfflineHandler(channel).offLines.remove(listener);

    }

    public static void regCommonOffLineListener(Channel channel, OffLineListener listener) {

        logger.debug("{} 注册Common掉线处理程序:", ChannelAttributeUtil.getChannelLogStr(channel), listener.getOffLineListenerName());

        commonOffLines.add(listener);
    }

    public static void removeCommonOffLineListener(Channel channel, OffLineListener listener) {

        logger.debug("{} 移除Common掉线处理程序:", ChannelAttributeUtil.getChannelLogStr(channel), listener.getOffLineListenerName());

        commonOffLines.remove(listener);
    }

    public void offLineExecute(Channel channel) {

        MessageHandlerUtil.execute(() -> {
            String channelStr = ChannelAttributeUtil.getChannelLogStr(channel);
            logger.info("{}断开网络进处理",channelStr);
            int size = offLines.size();

            for (int i = size - 1; i > -1; i--) {
                OffLineListener offLine = offLines.get(i);

                logger.debug("{} 执行channel掉线处理:{}", channelStr, offLine.getOffLineListenerName());

                offLine.executeOffLine(channel);

                logger.debug("{} 执行channel掉线处理:{} > 完成", channelStr, offLine.getOffLineListenerName());

            }
            size = commonOffLines.size();
            for (int i = size - 1; i > -1; i--) {
                OffLineListener offLine = commonOffLines.get(i);
                logger.debug("{} 执行common掉线处理:{}", channelStr, offLine.getOffLineListenerName());
                offLine.executeOffLine(channel);
                logger.debug("{} 执行common掉线处理:{} > 完成", channelStr, offLine.getOffLineListenerName());

            }

        });

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        offLineExecute(ctx.channel());
        super.channelInactive(ctx);

    }


    public static void main(String[] args) {
        System.out.println(OffLineHandler.class.getName());

    }
}

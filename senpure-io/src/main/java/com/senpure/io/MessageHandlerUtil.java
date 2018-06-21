package com.senpure.io;


import com.senpure.base.util.Assert;
import com.senpure.io.message.Message;
import com.senpure.io.handler.MessageHandler;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 罗中正 on 2017/5/27.
 */
public class MessageHandlerUtil {
    private static Map<Integer, MessageHandler> handlerMap = new HashMap<>();
    private static ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

    public static void execute(Runnable runnable) {
        service.execute(runnable);
    }

    public static void execute(Channel channel, Message message) {

        service.execute(() -> handlerMap.get(message.getMessageId()).execute(channel, message));
    }
    public static MessageHandler getHandler(int messageId) {
        return handlerMap.get(messageId);
    }
    public static void regMessageHandler(MessageHandler handler) {
        Assert.isNull(handlerMap.get(handler.handlerId()), handler.handlerId()+" -> " + handler.getEmptyMessage().getClass().getName() + "  处理程序已经存在");
        handlerMap.put(handler.handlerId(), handler);
    }

    public static Message getEmptyMessage(int messageId) {
        MessageHandler handler = handlerMap.get(messageId);
        if (handler == null) {
            return null;
        }
        return handler.getEmptyMessage();
    }
}

package com.senpure.io;


import com.senpure.base.util.Assert;
import com.senpure.io.bean.HandleMessage;
import com.senpure.io.handler.ComponentMessageHandler;
import com.senpure.io.message.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 罗中正 on 2017/5/27.
 */
public class ComponentMessageHandlerUtil {
    private static Map<Integer, ComponentMessageHandler> handlerMap = new HashMap<>();

    public static ComponentMessageHandler getHandler(int messageId) {
        return handlerMap.get(messageId);
    }


    public List<HandleMessage> regMessageIds = new ArrayList<>(128);
    public static void regMessageHandler(ComponentMessageHandler handler) {
        Assert.isNull(handlerMap.get(handler.handlerId()), handler.handlerId() + " -> " + handler.getEmptyMessage().getClass().getName() + "  处理程序已经存在");
        handlerMap.put(handler.handlerId(), handler);

        if (handler.regToGateway()) {
            HandleMessage handleMessage=new HandleMessage();

        }
    }

    public static Message getEmptyMessage(int messageId) {
        ComponentMessageHandler handler = handlerMap.get(messageId);
        if (handler == null) {
            return null;
        }
        return handler.getEmptyMessage();
    }

    public static List<Integer> getHandlerMessageIds() {
        return new ArrayList<>(handlerMap.keySet());
    }
}

package com.senpure.io.handler;


import com.senpure.io.message.Message;

/**
 * Created by 罗中正 on 2017/5/26.
 */
public abstract class AbstractInnerComponentMessageHandler<T extends Message> extends AbstractComponentMessageHandler<T> {

    @Override
    public boolean regToGateway() {
        return false;
    }
}

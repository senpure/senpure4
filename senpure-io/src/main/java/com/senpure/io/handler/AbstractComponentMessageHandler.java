package com.senpure.io.handler;


import com.senpure.base.util.Assert;
import com.senpure.io.ComponentMessageHandlerUtil;
import com.senpure.io.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.ResolvableType;

/**
 * Created by 罗中正 on 2017/5/26.
 */
public abstract class AbstractComponentMessageHandler<T extends Message> implements ComponentMessageHandler<T>, InitializingBean {
    protected Logger logger;
    protected Class<T> messageClass;

    public AbstractComponentMessageHandler() {
        this.logger = LoggerFactory.getLogger(getClass());
        ResolvableType resolvableType = ResolvableType.forClass(getClass());
        messageClass = (Class<T>) resolvableType.getSuperType().getGeneric(0).resolve();
    }

    @Override
    public T getEmptyMessage() {

        try {
            return messageClass.newInstance();
        } catch (Exception e) {

            Assert.error(e.toString());
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ComponentMessageHandlerUtil.regMessageHandler(this);

    }

    @Override
    public int messageType() {
        return 0;
    }

    @Override
    public boolean serverShare() {
        return false;
    }


    @Override
    public int valueType() {
        return 0;
    }

    @Override
    public long numStart() {
        return 0;
    }

    @Override
    public long numEnd() {
        return 0;
    }


    @Override
    public boolean regToGateway() {
        return true;
    }
}

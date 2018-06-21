package com.senpure.io.handler;

import com.senpure.base.util.Assert;
import com.senpure.io.MessageHandlerUtil;
import com.senpure.io.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.ResolvableType;

/**
 * Created by 罗中正 on 2017/5/26.
 */
public abstract class AbstractMessageHandler<T extends Message> implements MessageHandler<T>, InitializingBean {
    protected Logger logger;
    protected Class<T> messageClass;

    public AbstractMessageHandler() {
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

        MessageHandlerUtil.regMessageHandler(this);

    }
}

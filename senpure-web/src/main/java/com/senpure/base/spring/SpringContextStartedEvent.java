package com.senpure.base.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Repository;

@Repository
public class SpringContextStartedEvent implements
        ApplicationListener<ContextStartedEvent> {
    protected Logger logger;

    public SpringContextStartedEvent() {
        this.logger = LoggerFactory.getLogger(getClass());
    }

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {

        logger.debug("{}: startedEvent :{}",   event.getApplicationContext().getId(),event.toString());
    }

}

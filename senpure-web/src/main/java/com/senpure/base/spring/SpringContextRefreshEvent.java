package com.senpure.base.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

@Repository
public class SpringContextRefreshEvent implements
        ApplicationListener<ContextRefreshedEvent> {
    protected Logger logger;

    public SpringContextRefreshEvent() {
        this.logger = LoggerFactory.getLogger(getClass());
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        logger.debug("{}: refreshedEvent :{}",   event.getApplicationContext().getId(),event.toString());



    }
}

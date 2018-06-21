package com.senpure.base.spring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Repository;

@Repository
public class SpringContextClosedEvent implements ApplicationListener<ContextClosedEvent> {
	protected   Logger logger;

	public SpringContextClosedEvent() {
		logger= LoggerFactory.getLogger(getClass());
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {

		logger.debug("{}: closedEvent :{}",   event.getApplicationContext().getId(),event.toString());

	}
}

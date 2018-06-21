package com.senpure.base.spring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Repository;

@Repository
public class SpringContextStoppedEvent implements
        ApplicationListener<ContextStoppedEvent> {
	protected   Logger logger;

	public SpringContextStoppedEvent() {
		logger= LoggerFactory.getLogger(getClass());
	}

	@Override
	public void onApplicationEvent(ContextStoppedEvent event)

	{
		logger.debug("{}: stoppedEvent :{}",   event.getApplicationContext().getId(),event.toString());

	}

}

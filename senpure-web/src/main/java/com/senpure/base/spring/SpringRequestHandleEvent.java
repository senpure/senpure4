package com.senpure.base.spring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.RequestHandledEvent;


public class SpringRequestHandleEvent implements
        ApplicationListener<RequestHandledEvent> {
	private static Logger logger = LoggerFactory.getLogger(SpringRequestHandleEvent.class);
	@Override
	public void onApplicationEvent(RequestHandledEvent event) {


		logger.debug(event.toString());

	}

}

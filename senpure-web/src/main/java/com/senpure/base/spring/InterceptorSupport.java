package com.senpure.base.spring;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorSupport extends HandlerInterceptorAdapter{

	
	protected Logger logger;
	

	public InterceptorSupport() {
		
		super();
		logger= LoggerFactory.getLogger(getClass());
		
	}
	
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.debug("afterConcurrentHandlingStarted");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
}

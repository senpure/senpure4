package com.senpure.base.spring;


import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

/**
 * 跟踪访问的相关信息
 *
 * @author 罗中正
 * @version 1.0
 */

public class URLInfoInterceptor extends InterceptorSupport {
    private ThreadLocal<Long> requestTime = new ThreadLocal();

    @Override

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
        long c = System.currentTimeMillis() - requestTime.get();
        if (c > 5000) {
            logger.warn("{} {} ,用时:{} 毫秒,请检查相关程序代码" , request.getMethod(),request.getRequestURI(),c);
        } else {
            logger.debug("{} {} ,用时:{} 毫秒" , request.getMethod(),request.getRequestURI(),c);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        if (logger.isDebugEnabled() && modelAndView != null) {
            logger.debug("{} {} > {}",request.getMethod(), request.getRequestURI(), modelAndView.getViewName());
        }
        if (logger.isTraceEnabled() && modelAndView != null) {

            Map<String, Object> map = modelAndView.getModel();
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                logger.trace("key={}，value={} ", key, map.get(key));

            }
        }
    }

    private static String key = URLInfoInterceptor.class + ".time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //ThreadLocalContext.getContext().set(key, System.currentTimeMillis());
        requestTime.set(System.currentTimeMillis());
        logger.debug("preHandle:{} {}", request.getMethod(),request.getRequestURI());
        if (logger.isTraceEnabled()) {

            Enumeration<String> e = request.getHeaderNames();
            StringBuilder sb = new StringBuilder();
            while (e.hasMoreElements()) {
                String key = e.nextElement();
                sb.append(key).append(":").append(request.getHeader(key)).append(",");
            }
            logger.trace("header:{}", sb.toString());
        }
        return true;
    }

}

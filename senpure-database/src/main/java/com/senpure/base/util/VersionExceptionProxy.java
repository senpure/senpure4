package com.senpure.base.util;


import com.senpure.base.annotation.Retry;
import com.senpure.base.exception.OptimisticLockingFailureException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by DZ on 2016-07-19 16:25
 */
@Aspect
@Component
public class VersionExceptionProxy {

    private ThreadLocal<Proxy> proxy = new ThreadLocal();
    private static Logger logger = LoggerFactory.getLogger(VersionExceptionProxy.class);
    public VersionExceptionProxy() {
    }
    @Pointcut("@annotation(com.senpure.base.annotation.Retry)")
    private void proxyAspect() {

    }

    @Around("proxyAspect()")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        Object result ;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            logger.info("throwable class {}", throwable.getClass());
            if (throwable instanceof OptimisticLockingFailureException) {
                Proxy p = proxy.get();
                if (p == null) {
                    p = new Proxy();
                    java.lang.reflect.Method method = ((MethodSignature) pjp.getSignature()).getMethod();
                    p.method = method;
                    Retry retry = method.getAnnotation(Retry.class);
                    p.retryTime = retry.retryTimes();
                    p.invertal=retry.interval();
                    proxy.set(p);
                }
                if (p.times >= p.retryTime) {
                    logger.warn("重试次数上限 {} ", p.retryTime);
                    throw throwable;
                }

                p.times++;
                Thread.sleep(p.invertal);
                logger.debug("进行 第{}次重试",p.times);
                return execute(pjp);

            } else {
                throw throwable;
            }


        }
        return result;
    }

    private class Proxy {

        int times;
        java.lang.reflect.Method method;
        int retryTime;
        int invertal;
    }


}

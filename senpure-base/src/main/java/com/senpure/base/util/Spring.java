package com.senpure.base.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by 罗中正 on 2017/8/28.
 */
@Component
public class Spring implements ApplicationContextAware {

    protected static Logger logger = LogManager.getLogger(Spring.class);
    private static AbstractApplicationContext act;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {

        regApplicationContext(context);

    }

    public static void regApplicationContext(ApplicationContext context) {
        if (act == null) {
           // logger.info("Spring 获取ApplicationContext上下文:applicationName:" + context.getApplicationName() + ",displayName:" + context.getDisplayName() + ",id:" + context.getId());
            act = (AbstractApplicationContext) context;
        }
    }

    public static void regSingleBean(String name, Object bean) {


        act.getBeanFactory().registerSingleton(name, bean);


    }

    public static <T> T getBean(Class<T> type) {

        return act.getBean(type);
    }

    public static Object getBean(String name) {

        return act.getBean(name);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {

        return act.getBeansOfType(type);
    }

    public static void exit(ExitCodeGenerator... exitCodeGenerators) {
        if (act != null) {
            SpringApplication.exit(act, exitCodeGenerators);
        }
        else {
            System.exit(0);

        }

    }
}

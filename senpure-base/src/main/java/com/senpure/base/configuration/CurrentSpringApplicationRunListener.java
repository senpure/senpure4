package com.senpure.base.configuration;

import com.senpure.base.AppEvn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


public class CurrentSpringApplicationRunListener implements SpringApplicationRunListener, ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(getClass());

    public CurrentSpringApplicationRunListener(SpringApplication springApplication, String[] args) {

    }

    @Override
    public void starting() {

    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
            //保证开发阶段的的有几个classpath 时rootPath正确性
        StackTraceElement[] statcks = Thread.currentThread()
                .getStackTrace();

        StackTraceElement statck = statcks[statcks.length - 1];
        Class clazz=null;
        try {
            clazz = Class.forName(statck.getClassName());
            AppEvn.markClassRootPath(clazz);
        } catch (Exception e) {
            logger.error("error", e);
        }
        AppEvn.installAnsiConsole(clazz);
        logger.debug("{}={}", "spring.output.ansi.enabled", environment.getProperty("spring.output.ansi.enabled"));

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext configurableApplicationContext) {
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {
    }

    @Override
    public void started(ConfigurableApplicationContext configurableApplicationContext) {

    }

    @Override
    public void running(ConfigurableApplicationContext configurableApplicationContext) {

    }

    @Override
    public void failed(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {

    }


    @Override
    public void run(ApplicationArguments args) throws Exception {


    }
}

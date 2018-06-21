package com.senpure.base.util;

import com.senpure.base.AppEvn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.CompositeTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.slf4j.Log4jLogger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Created by 罗中正 on 2018/4/8 0008.
 */
public class LoggerUtil {
    private static ConcurrentHashMap<Integer, LoggerAndAppender> loggers = new java.util.concurrent.ConcurrentHashMap();
    private static String FILE_PATH = "logs";
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMdd");


    private static  ConcurrentHashMap<Integer, AtomicLong> atomics = new ConcurrentHashMap();


    private static String getLogPath() {

        if (AppEvn.isWindowsOS()) {
            return FILE_PATH + "/" + dateFormat.format(new Date());
        }
        return "logs/" + dateFormat.format(new Date());
    }

    public static void releaseLogger(int roomId) {
        LoggerAndAppender loggerAndAppender = loggers.remove(roomId);
        if (loggerAndAppender != null) {

            final LoggerContext ctx = (LoggerContext) LogManager.getContext(true);
            final Configuration config = ctx.getConfiguration();
            config.removeLogger("game.ybmj.room." + roomId);
            loggerAndAppender.appender.stop();
            //  System.out.println(loggerAndAppender.appender.toString());
            loggerAndAppender.logger = null;
        }
    }

    public static org.slf4j.Logger getLogger(int roomId) {
        LoggerAndAppender loggerAndAppender = loggers.get(roomId);
        if (loggerAndAppender != null) {
            return loggerAndAppender.logger;
        }
        String fileName = new File(getLogPath(), File.separator + roomId + File.separator + "log.log").getAbsolutePath();
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(true);
        final Configuration config = ctx.getConfiguration();

        DefaultRolloverStrategy strategy = DefaultRolloverStrategy.createStrategy("20", null, null, null, null, false, config);
        TimeBasedTriggeringPolicy timeBasedTriggeringPolicy = TimeBasedTriggeringPolicy
                .createPolicy("1", "true");
        SizeBasedTriggeringPolicy sizeBasedTriggeringPolicy = SizeBasedTriggeringPolicy.createPolicy("5120 KB");
        CompositeTriggeringPolicy compositeTriggeringPolicy = CompositeTriggeringPolicy.createPolicy(timeBasedTriggeringPolicy, sizeBasedTriggeringPolicy);
        // CompositeTriggeringPolicy compositeTriggeringPolicy = CompositeTriggeringPolicy.createPolicy(sizeBasedTriggeringPolicy);
        AtomicLong atomicLong = atomics.get(roomId);
        if (atomicLong == null) {
            atomicLong = new AtomicLong();
            atomics.putIfAbsent(roomId, atomicLong);
        }

        RollingFileAppender appender = RollingFileAppender.newBuilder()
               // .withAppend(true)
                .withName(roomId+"["+atomicLong.incrementAndGet()+"]" + "RollingAppender")
                //.withBufferedIo(true)
                //.withBufferSize(2048)

                .withImmediateFlush(false)
                .withFileName(fileName)
                .withFilePattern(getLogPath() + "/" + roomId + "/log.%d{HH}_%i.log")
                .withPolicy(compositeTriggeringPolicy)
                .withLayout(
                        PatternLayout.newBuilder().withPattern("%d{HH:mm:ss.SSS} [%t] %-5level - %msg%n").build())
                .withStrategy(strategy)
                .build();


        appender.start();
        String loggerName = "game.ybmj.room." + roomId;
        org.apache.logging.log4j.core.Logger logger = ctx.getLogger(loggerName);
        Iterator<Appender> iterator = logger.getAppenders().values().iterator();
        while (iterator.hasNext()) {
            Appender ader = iterator.next();
            //取消其他日志
            // logger.removeAppender(ader);
        }
        logger.addAppender(appender);
        loggerAndAppender = new LoggerAndAppender();
        loggerAndAppender.logger = new Log4jLogger(logger, loggerName);
        loggerAndAppender.appender = appender;
        loggers.putIfAbsent(roomId, loggerAndAppender);
        return loggers.get(roomId).logger;
    }
    private static class LoggerAndAppender {
        Log4jLogger logger;
        Appender appender;

    }
}

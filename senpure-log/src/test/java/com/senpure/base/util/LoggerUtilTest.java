package com.senpure.base.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 罗中正 on 2018/4/8 0008.
 */

public class LoggerUtilTest {


    private List<String> getLogStr() {
        int num = 5000;
        System.out.println(ReadNumber.read(num));
        List<String> logStrs = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            logStrs.add("this is a log " + i);

        }
        return logStrs;
    }



    public void loggerTest() {
       // System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");

        Logger logger = LoggerFactory.getLogger(getClass());
        List<String> logStrs = getLogStr();
        long now = System.currentTimeMillis();
        for (String str : logStrs) {
            logger.debug(str);
        }
        System.out.println(System.currentTimeMillis() - now);

    }

    public void loggerTest2() {
       // System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");

        int num=500;
        List<Logger> loggers = new ArrayList<>(16 << 4);
        for (int i = 0; i < num; i++) {
            Logger logger = LoggerUtil.getLogger(i+1);
            loggers.add(logger);
        }
        List<String> logStrs = getLogStr();
        long now = System.currentTimeMillis();
        for (String str : logStrs) {
            int i = getI(num);
            loggers.get(i).debug(str);
        }
        System.out.println(System.currentTimeMillis() - now);

    }


    public void logger500Test() {
        // System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        int num = 500;
        List<Logger> loggers = new ArrayList<>(16 << 4);
        for (int i = 0; i < num; i++) {
            Logger logger = LoggerUtil.getLogger(i+1);

            loggers.add(logger);
        }
        int c = 0;


        List<String> logStrs = getLogStr();

        long  time=System.currentTimeMillis();
        for (String str : logStrs) {
            for (Logger logger : loggers) {

                logger.debug(str);
            }
        }
        System.out.println(System.currentTimeMillis()-time);
    }


    java.util.concurrent.atomic.AtomicInteger atomicInteger = new AtomicInteger(-1);
    private int getI(int size) {
        int i = atomicInteger.incrementAndGet();
        if (i >= size) {
            atomicInteger.compareAndSet(i, -1);
            return getI(size);
        }
        return i;
    }

    public static void main(String[] args) {
       System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");

        LoggerUtilTest loggerUtilTest = new LoggerUtilTest();
        //loggerUtilTest.loggerTest();
        System.out.println("----");
        loggerUtilTest.loggerTest2();
       // loggerUtilTest.logger500Test();
    }
}

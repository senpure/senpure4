package com.senpure.base.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/1/20.
 */
public class BaseConfiguration {

    protected Logger logger;

    public BaseConfiguration() {
        logger= LoggerFactory.getLogger(getClass());
    }
}

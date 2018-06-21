package com.senpure.base.configuration;


import com.senpure.base.util.IDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 罗中正 on 2017/8/25.
 */
@Configuration
@ConfigurationProperties(
        prefix = "store"
)

public class IDGeneratorAutoConfiguration {

    protected Logger logger = LoggerFactory.getLogger(IDGeneratorAutoConfiguration.class);
    private int datacenterId=0;
    private int workerId=0;
    @Bean
    public IDGenerator getIdGenerator() {
        logger.debug("workerId is {} datacenterId is {}",workerId,datacenterId);
        return new IDGenerator(workerId, datacenterId);
    }

    public int getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(int datacenterId) {
        this.datacenterId = datacenterId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
}

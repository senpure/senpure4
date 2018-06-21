package com.senpure.base.init;

import com.senpure.base.cache.LocalRemoteCacheManager;
import com.senpure.base.spring.SpringContextRefreshEvent;
import com.senpure.base.util.Spring;
import com.senpure.base.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.endpoint.web.ExposableWebEndpoint;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by 罗中正 on 2017/7/27.
 */
@Component
public class SystemInfoPrinter extends SpringContextRefreshEvent implements ApplicationRunner, ApplicationContextAware {

    Logger logger = LoggerFactory.getLogger(getClass());
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        CacheManager cacheManager = Spring.getBean(CacheManager.class);
        if (cacheManager instanceof LocalRemoteCacheManager) {
            LocalRemoteCacheManager localRemoteCacheManager = (LocalRemoteCacheManager) cacheManager;
            logger.info("采用spring cache 本机缓存\n {}", localRemoteCacheManager.getLocalNames());
        }

        WebMvcEndpointHandlerMapping endpointHandlerMapping = applicationContext.getBean(WebMvcEndpointHandlerMapping.class);


        Collection<ExposableWebEndpoint> mvcEndpoints = endpointHandlerMapping.getEndpoints();
        StringBuilder sb = new StringBuilder();
        String basePath="/actuator/";
        mvcEndpoints.forEach(mvcEndpoint -> {
            sb.append("@ExtPermission(value ={").append("\"").
                    append(basePath). append(mvcEndpoint.getRootPath()).append("\",\"").
                    append(basePath). append(mvcEndpoint.getRootPath()).
                    append(".json").append("\"").append("}")
                    .append(",name = \"").append("/actuator_read").append("\"")
                    .append(")")
                    .append("\n");

            sb.append("public void ").append("read")
                    .append(StringUtil.toUpperFirstLetter(mvcEndpoint.getRootPath()))
                    .append("(){").append("\n").append("}").append("\n");

        });


         // logger.info("\n{}",sb.toString());


    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

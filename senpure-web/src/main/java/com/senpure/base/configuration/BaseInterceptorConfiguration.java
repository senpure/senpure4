package com.senpure.base.configuration;

import com.senpure.base.spring.BaseMultipleInterceptor;
import com.senpure.base.spring.URLInfoInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/1/20.
 */
//@Configuration
@ConditionalOnMissingBean(name = "baseMultipleInterceptor")
public class BaseInterceptorConfiguration extends WebMvcConfigurerAdapter {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private URLInfoInterceptor urlInfoInterceptor;
    @Autowired
    private BaseMultipleInterceptor multipleInterceptor;


    @Bean
    public URLInfoInterceptor getUrlInfoInterceptor() {
        return new URLInfoInterceptor();
    }

    @Bean
    public BaseMultipleInterceptor getMultipleInterceptor() {

        return new BaseMultipleInterceptor();
    }

    public BaseInterceptorConfiguration() {
        super();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        logger.debug("注册拦截器");
        // urlInfoInterceptor=getUrlInfoInterceptor();
        registry.addInterceptor(urlInfoInterceptor);
        // multipleInterceptor=getMultipleInterceptor();
        registry.addInterceptor(multipleInterceptor).excludePathPatterns("/resources/**");
        logger.debug(urlInfoInterceptor.toString());
        super.addInterceptors(registry);

    }
}

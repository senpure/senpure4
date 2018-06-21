package com.senpure.base.configuration;

import com.senpure.base.spring.MultipleInterceptor;
import com.senpure.base.spring.URLInfoInterceptor;
import com.senpure.base.spring.VerifyInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 *
 * @author Administrator
 * @date 2017/1/20
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private URLInfoInterceptor urlInfoInterceptor;

    @Autowired
    private MultipleInterceptor multipleInterceptor;
    @Autowired
    private VerifyInterceptor verifyInterceptor;
    @Autowired
    WebMvcEndpointHandlerMapping endpointHandlerMapping;
    RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Bean
    public URLInfoInterceptor getUrlInfoInterceptor() {
        return new URLInfoInterceptor();
    }

    @Bean
    public MultipleInterceptor getMultipleInterceptor() {

        return new MultipleInterceptor();
    }

    @Bean
    public VerifyInterceptor getVerifyInterceptor() {

        return new VerifyInterceptor();
    }


    public InterceptorConfiguration() {
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
      //  registry.addInterceptor(verifyInterceptor).excludePathPatterns("/authorize/login*", "/authorize/exit");
       // registry.addInterceptor(multipleInterceptor).excludePathPatterns("/resources/**");
        registry.addInterceptor(multipleInterceptor);



    }
}

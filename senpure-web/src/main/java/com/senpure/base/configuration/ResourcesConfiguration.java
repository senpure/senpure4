package com.senpure.base.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2017/1/22.
 */
@Configuration

public class ResourcesConfiguration implements WebMvcConfigurer {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/resources/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }


}

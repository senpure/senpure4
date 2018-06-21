package com.senpure.base.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Created by Administrator on 2017/1/20.
 */
@Configuration
@ConditionalOnMissingBean(name = "localeResolver")
public class LocaleResolverConfiguration {

    Logger logger= LoggerFactory.getLogger(getClass());
    @Bean
    public LocaleResolver localeResolver() {
        logger.debug("注册localeResolver [SessionLocaleResolver]");
        SessionLocaleResolver slr = new SessionLocaleResolver();
        //设置默认区域,
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }

}

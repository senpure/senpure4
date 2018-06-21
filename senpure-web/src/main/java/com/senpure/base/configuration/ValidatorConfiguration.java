package com.senpure.base.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2017/1/22.
 */
@Configuration

public class ValidatorConfiguration implements WebMvcConfigurer {
    Logger logger = LoggerFactory.getLogger(ValidatorConfiguration.class);

    //@Autowired
   // MessageSource messageSource;

    @Override
    public Validator getValidator() {

        LocalValidatorFactoryBean validator= new    LocalValidatorFactoryBean();
        validator.setProviderClass(org.hibernate.validator.HibernateValidator.class);

       // ReloadableResourceBundleMessageSource source=new ReloadableResourceBundleMessageSource();
        ResourceBundleMessageSource source=new ResourceBundleMessageSource();
       // source.setBasename("classpath:i18n/validate/validate");
        source.setBasenames("i18n/validate/validate");

       // logger.info(" 注册messageSource"+messageSource);
        //validator.setValidationMessageSource(messageSource);
        logger.debug("注册messageSource"+source);
       validator.setValidationMessageSource(source);

        return validator;
    }



}

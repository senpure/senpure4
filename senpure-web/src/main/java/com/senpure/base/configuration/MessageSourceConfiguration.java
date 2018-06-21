package com.senpure.base.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2017/1/20.
 */
//MessageSourceAutoConfiguration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(
        prefix = "spring.messages"
)
public class MessageSourceConfiguration  extends BaseConfiguration {
    private  boolean  useCodeAsDefaultMessage=true;
    private String basename = "messages";
    private Charset encoding = Charset.forName("UTF-8");
    private int cacheSeconds = -1;
    private boolean fallbackToSystemLocale = true;
    private boolean alwaysUseMessageFormat = false;

  //  private  MessageSource messageSource;
    @Bean
    public MessageSource messageSource()
    {
       // ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();

        ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
        if(StringUtils.hasText(this.basename)) {

           // logger.info("basename is "+basename);
          //  System.out.println("baseNmae="+basename);
          // String [] bs= StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(this.basename));

            messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(StringUtils.trimAllWhitespace(this.basename)));
        }

        if(this.encoding != null) {
            messageSource.setDefaultEncoding(this.encoding.name());
        }

        messageSource.setFallbackToSystemLocale(this.fallbackToSystemLocale);
        messageSource.setCacheSeconds(this.cacheSeconds);
        messageSource.setAlwaysUseMessageFormat(this.alwaysUseMessageFormat);

        messageSource.setUseCodeAsDefaultMessage(this.useCodeAsDefaultMessage);


       // this.messageSource=messageSource;
        return messageSource;
    }



    public boolean isUseCodeAsDefaultMessage() {
        return useCodeAsDefaultMessage;
    }

    public void setUseCodeAsDefaultMessage(boolean useCodeAsDefaultMessage) {
        this.useCodeAsDefaultMessage = useCodeAsDefaultMessage;
    }

    public String getBasename() {
        return basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }

    public Charset getEncoding() {
        return encoding;
    }

    public void setEncoding(Charset encoding) {
        this.encoding = encoding;
    }

    public int getCacheSeconds() {
        return cacheSeconds;
    }

    public void setCacheSeconds(int cacheSeconds) {
        this.cacheSeconds = cacheSeconds;
    }

    public boolean isFallbackToSystemLocale() {
        return fallbackToSystemLocale;
    }

    public void setFallbackToSystemLocale(boolean fallbackToSystemLocale) {
        this.fallbackToSystemLocale = fallbackToSystemLocale;
    }

    public boolean isAlwaysUseMessageFormat() {
        return alwaysUseMessageFormat;
    }

    public void setAlwaysUseMessageFormat(boolean alwaysUseMessageFormat) {
        this.alwaysUseMessageFormat = alwaysUseMessageFormat;
    }
}

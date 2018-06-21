package com.senpure.base.configuration;

import com.senpure.base.util.DateFormatUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by 罗中正 on 2018/1/19 0019.
 */
@Configuration
public class ConverterConfiguration {


    @Bean
    public Converter<String, Date> dateConverter() {

        return new Str2Date();
    }
    class Str2Date implements Converter<String, Date> {
        @Override
        public Date convert(String s) {
            try {
                if (s != null && s.length() > 0) {
                    return DateFormatUtil.getDateFormat(DateFormatUtil.DFP_Y2S).parse(s);
                }
                return null;
            } catch (ParseException e) {
                return null;
            }
        }
    }

}

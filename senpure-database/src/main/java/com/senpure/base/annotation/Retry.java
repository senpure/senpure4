package com.senpure.base.annotation;

import java.lang.annotation.*;

/**
 * Created by DZ on 2016-07-19 16:21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Retry {
    int retryTimes() default  3;
    int interval() default 100;
}

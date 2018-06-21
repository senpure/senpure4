package com.senpure.base.result;

import java.lang.annotation.*;

/**
 * Created by DZ on 2016-06-28 14:07
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Message {
    String message() default "" ;

    String describe() default "";
}

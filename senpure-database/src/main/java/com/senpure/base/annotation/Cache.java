package com.senpure.base.annotation;

/**
 * Created by 罗中正 on 2017/10/17.
 */
public @interface Cache {
    boolean value() default  true;
    boolean remote() default  true;
    boolean local() default  false;
    boolean map() default  false;

}

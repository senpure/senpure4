package com.senpure.base.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PermissionVerify {
    /**
     * 是否开启检查
     *
     * @return
     */
    boolean verify() default true;

    /**
     * 是否开启检查登陆
     *
     * @return
     */
    boolean login() default true;

    /**
     * 可读的名字
     *
     * @return
     */
    String value() default "";

    /**
     * 唯一标识
     *
     * @return
     */
    String name() default "";




}

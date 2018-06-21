package com.senpure.base.annotation;

/**
 * Created by 罗中正 on 2017/6/16.
 */

import java.lang.annotation.*;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ResourcesVerify.class)
public @interface ResourceVerify {
    /**
     * 验证器名字
     * @return
     */
    String value();

    /**
     * 生成到数据库使用如 修改我的名字
     * @return
     */
    String permissionName() default "";

    int offset() default 1;



}


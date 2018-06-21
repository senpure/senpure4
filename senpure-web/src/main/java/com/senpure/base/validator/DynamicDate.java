package com.senpure.base.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by 罗中正 on 2017/5/15.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PatternDateValidator.class)
@Documented
public @interface DynamicDate {
    String message() default "{time.format.error}";
   // String pattern() default "yyyy-MM-dd HH:mm:ss";
   // String date() default "1970-01-01 08:00:00";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

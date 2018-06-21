package com.senpure.base.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD,ElementType.TYPE,ElementType.ANNOTATION_TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy = IdsValidator.class)  
@Documented
public @interface Ids {
	     String message() default "{ids.format.error}";  
	    Class<?>[] groups() default {};  
	    Class<? extends Payload>[] payload() default {}; 
	 
}

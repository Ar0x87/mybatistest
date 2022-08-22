package com.null01.validators.annotations;

import com.null01.validators.implementations.OneOfTwoImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = OneOfTwoImpl.class)
@Documented
public @interface OneOfTwo {

    String message() default "Allowed delete by only one parameter";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

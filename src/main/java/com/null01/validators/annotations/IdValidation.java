package com.null01.validators.annotations;

import com.null01.validators.implementations.IdValidationImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = IdValidationImpl.class)
@Documented
public @interface IdValidation {

    String message() default "{Id in request is invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

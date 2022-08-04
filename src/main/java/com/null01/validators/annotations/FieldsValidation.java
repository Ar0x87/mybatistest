package com.null01.validators.annotations;

import com.null01.validators.implementations.FieldsValidationImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = FieldsValidationImpl.class)
@Documented
public @interface FieldsValidation {

    String message() default "Empty hotelname and address";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
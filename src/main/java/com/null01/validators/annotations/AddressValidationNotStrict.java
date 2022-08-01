package com.null01.validators.annotations;

import com.null01.validators.implementations.AddressValidationNotStrictImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = AddressValidationNotStrictImpl.class)
@Documented
public @interface AddressValidationNotStrict {

    String message() default "{Name in request is invalid}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

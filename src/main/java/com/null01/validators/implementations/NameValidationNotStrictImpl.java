package com.null01.validators.implementations;

import com.null01.validators.annotations.NameValidationNotStrict;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidationNotStrictImpl implements ConstraintValidator<NameValidationNotStrict, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if (value.length() > 50) {
                return false;
            } else {
                return true;
            }
        }
        catch (NullPointerException npe) {
            return true;
        }
    }
}

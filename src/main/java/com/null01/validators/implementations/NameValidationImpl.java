package com.null01.validators.implementations;

import com.null01.validators.annotations.NameValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidationImpl implements ConstraintValidator<NameValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !value.equals("") && value.length() < 51 && value.length() > 1) {
            return true;
        } else {
            return false;
        }
    }

}
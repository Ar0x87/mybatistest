package com.null01.validators.implementations;

import com.null01.validators.annotations.NameValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NameValidationImpl implements ConstraintValidator<NameValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.equals("") || value == null || value.length() > 50 || value.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

}
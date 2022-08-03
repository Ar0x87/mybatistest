package com.null01.validators.implementations;

import com.null01.validators.annotations.IdValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdValidationImpl implements ConstraintValidator<IdValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if (value != null && !value.equals("") && value.length() > 0 && Integer.parseInt(value) > 0) {
                return true;
            } else {
                return false;
            }
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

}

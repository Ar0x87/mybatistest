package com.null01.validators.implementations;

import com.null01.validators.annotations.AddressValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class AddressValidationImpl implements ConstraintValidator<AddressValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.equals("") || value == null || value.length() > 20) {
            return false;
        } else {
            return true;
        }
    }

}
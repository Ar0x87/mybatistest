package com.null01.validators.implementations;

import com.null01.requests.StructureForPatch;
import com.null01.validators.annotations.FieldsValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldsValidationImpl implements ConstraintValidator<FieldsValidation, StructureForPatch> {

    @Override
    public boolean isValid(StructureForPatch value, ConstraintValidatorContext context) {
        if ((value.getHotelname() == null || value.getHotelname().equals("")) && (value.getAddress() == null || value.getAddress().equals(""))) {
            return false;
        } else {
            return true;
        }
    }

}

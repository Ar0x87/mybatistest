package com.null01.validators.implementations;

import com.null01.requests.StructureForDel;
import com.null01.validators.annotations.OneOfTwo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OneOfTwoImpl implements ConstraintValidator<OneOfTwo, StructureForDel> {

    @Override
    public boolean isValid(StructureForDel value, ConstraintValidatorContext context) {
        if ((value.getId() != null && !value.getId().equals("")) && (value.getHotelname() == null || value.getHotelname().equals(""))) {
            return true;
        } else if ((value.getId() == null || value.getId().equals("")) && (value.getHotelname() != null && !value.getHotelname().equals(""))) {
            return true;
        } else {
            return false;
        }
    }

}

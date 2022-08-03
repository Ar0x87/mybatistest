package com.null01.validators.implementations;

import com.null01.validators.annotations.AddressValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressValidationImpl implements ConstraintValidator<AddressValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern p = Pattern.compile("(г. {1})(\\D*)(, ул. {1})(\\D*)(, д. {1})(\\d{1,4})");
        if (value != null && !value.equals("") && value.length() < 100) {
            Matcher m = p.matcher(value);
            if (m.find()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
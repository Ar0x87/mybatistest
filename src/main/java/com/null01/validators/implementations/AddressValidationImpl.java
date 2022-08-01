package com.null01.validators.implementations;

import com.null01.validators.annotations.AddressValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddressValidationImpl implements ConstraintValidator<AddressValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern p1 = Pattern.compile("(г. {1})(\\D*)(, ул. {1})(\\D*)(, д. {1})(\\d{1,4})");
        Pattern p2 = Pattern.compile("(\\D{2,50})");
        Matcher m1 = p1.matcher(value);
        Matcher m2 = p2.matcher(value);
        if (!value.equals("") && value.length() < 100) {
            if (m1.find() || m2.find()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
package com.null01.requests;

import com.null01.validators.annotations.FieldsValidation;
import com.null01.validators.annotations.IdValidation;
import com.null01.validators.annotations.NameValidationNotStrict;
import com.null01.validators.annotations.AddressValidationNotStrict;
import lombok.Data;

@Data
@FieldsValidation
public class StructureForPatch extends RequestStructureFullLine {

    @IdValidation(message = "Invalid ID")
    private String id;

    @NameValidationNotStrict(message = "Invalid Name")
    private String hotelname;

    @AddressValidationNotStrict(message = "Invalid Address")
    private String address;

}

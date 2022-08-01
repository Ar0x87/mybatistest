package com.null01.requests;

import com.null01.validators.annotations.IdValidation;
import com.null01.validators.annotations.NameValidationNotStrict;
import com.null01.validators.annotations.AddressValidationNotStrict;
import lombok.Data;

@Data
public class StructureForPatch extends RequestStructureFullLine {

    @IdValidation(message = "Invalid id")
    private String id;

    @NameValidationNotStrict(message = "Invalid hotelname")
    private String hotelname;

    @AddressValidationNotStrict(message = "Invalid address")
    private String address;

}

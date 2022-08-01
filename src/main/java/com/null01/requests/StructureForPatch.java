package com.null01.requests;

import com.null01.validators.annotations.IdValidation;
import com.null01.validators.annotations.NameValidationNotStrict;
import com.null01.validators.annotations.AddressValidationNotStrict;
import lombok.Data;

@Data
public class StructureForPatch extends RequestStructureFullLine {

    @IdValidation
    private String id;

    @NameValidationNotStrict
    private String hotelname;

    @AddressValidationNotStrict
    private String address;

}

package com.null01.requests;

import com.null01.validators.annotations.AddressValidation;
import com.null01.validators.annotations.IdValidation;
import com.null01.validators.annotations.NameValidation;
import lombok.Data;

@Data
public class StructureForPut extends RequestStructureFullLine {

    @IdValidation
    private String id;

    @NameValidation
    private String hotelname;

    @AddressValidation
    private String address;

}

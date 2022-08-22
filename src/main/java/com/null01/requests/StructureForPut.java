package com.null01.requests;

import com.null01.validators.annotations.AddressValidation;
import com.null01.validators.annotations.IdValidation;
import com.null01.validators.annotations.NameValidation;
import lombok.Data;

@Data
public class StructureForPut extends RequestStructureFullLine {

    @IdValidation(message = "Invalid ID")
    private String id;

    @NameValidation(message = "Invalid Name")
    private String hotelname;

    @AddressValidation(message = "Invalid Name")
    private String address;

}

package com.null01.requests;

import com.null01.validators.annotations.AddressValidation;
import com.null01.validators.annotations.NameValidation;
import lombok.Data;

@Data
public class RequestStructure {

    @NameValidation(message = "Invalid Name")
    private String hotelname;

    @AddressValidation(message = "Invalid Address")
    private String address;

}
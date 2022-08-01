package com.null01.requests;

import com.null01.validators.annotations.AddressValidation;
import com.null01.validators.annotations.NameValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RequestStructure {

    @NotBlank
    @NameValidation
    private String hotelname;

    @NotBlank
    @AddressValidation
    private String address;

}
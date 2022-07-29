package com.null01.requests;

import com.null01.validators.annotations.AddressValidation;
import com.null01.validators.annotations.NameValidation;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@Data
@Validated
public class RequestStructure {


    //@Length(min = 2, max = 30)
    //@NameValidation
    @Pattern(regexp = "\\D{2,3}", message = "Инвалид")
    private String hotelname;

    //@NotBlank
    //@Length(min = 2, max = 20)
    //@AddressValidation
    @Pattern(regexp = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$", message = "Инвалид")
    private String address;

    @AssertFalse
    public boolean check() {
        System.out.println(hotelname);
        return false;
    }
}
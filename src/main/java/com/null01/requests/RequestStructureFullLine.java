package com.null01.requests;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Validated
public class RequestStructureFullLine {

    @Min(1)
    @NotEmpty
    private String id;

    @NotBlank
    @Size(max = 30)
    private String hotelname;

    @NotBlank
    @Size(max = 20)
    private String address;

}
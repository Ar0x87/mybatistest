package com.null01.requests;

import com.null01.validators.annotations.OneOfTwo;
import lombok.Data;

@Data
@OneOfTwo
public class StructureForDel {

    private String id;
    private String hotelname;

}

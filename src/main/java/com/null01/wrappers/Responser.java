package com.null01.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Responser {
    private Integer code;
    private String message;
    private Boolean success;
}
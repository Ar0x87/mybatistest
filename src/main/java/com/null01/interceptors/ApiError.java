package com.null01.interceptors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private Integer errorCode;
    private String errorMessage;
    private Boolean success;
    private String description;
}

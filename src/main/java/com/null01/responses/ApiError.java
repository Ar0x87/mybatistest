package com.null01.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError extends ApiResponse{
    private Integer code;
    private String message;
    private Boolean success;
    private String description;
}

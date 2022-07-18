package com.null01.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiAnswer extends ApiResponse{
    private Integer code;
    private String message;
    private Boolean success;
    private ArrayList<Integer> result;
}

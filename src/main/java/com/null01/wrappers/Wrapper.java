package com.null01.wrappers;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.null01.models.IWrapperModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wrapper extends Responser implements IWrapperModel {

    @JsonUnwrapped
    private Integer code;
    private String message;
    private Boolean success;
    Object result;

    @Override
    public void setData(Responser responser) {
        code = responser.getCode();
        message = responser.getMessage();
        success = responser.getSuccess();

    }

    @Override
    public void setBody(Object object) {
        result = object;
    }
}
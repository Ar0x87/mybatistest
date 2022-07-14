package com.null01.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong id!")
public class EmptyBodyException extends Exception{
    public EmptyBodyException(String message){
        super(message);
    }
}

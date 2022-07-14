package com.null01.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, reason = "Entry with same id already exists!")
public class AlreadyExistException extends Exception{
    public AlreadyExistException(String message){
        super(message);
    }
}
package com.null01.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Entry do not exist!")
public class UnexistanceException extends Exception{
    public UnexistanceException(String message) {
        super(message);
    }
}
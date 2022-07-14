package com.null01.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Wrong id!")
public class MisstargetException extends Exception{
    public MisstargetException(String message) {
        super(message);
    }
}
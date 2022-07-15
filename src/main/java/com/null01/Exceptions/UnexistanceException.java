package com.null01.Exceptions;

public class UnexistanceException extends RuntimeException{
    public UnexistanceException(String message) {
        super(message);
    }
}
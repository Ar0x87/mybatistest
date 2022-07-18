package com.null01.interceptors;

import com.null01.exceptions.AlreadyExistException;
import com.null01.exceptions.EmptyBodyException;
import com.null01.exceptions.MisstargetException;
import com.null01.exceptions.UnexistanceException;
import com.null01.responses.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    protected ResponseEntity<Object> AlreadyExist(RuntimeException e, WebRequest request) {
        ApiError apiError = new ApiError(412, "EXISTENCE_CONFLICT",  false, e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(EmptyBodyException.class)
    protected ResponseEntity<Object> EmptyBody(RuntimeException e, WebRequest request) {
        ApiError apiError = new ApiError(406, "EMPTY_REQUEST_EXCEPTION", false, e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MisstargetException.class)
    protected ResponseEntity<Object> Misstarget(RuntimeException e, WebRequest request) {
        ApiError apiError = new ApiError(416, "MISSTARGETING", false, e.getMessage());
        return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UnexistanceException.class, NumberFormatException.class})
    protected ResponseEntity<Object> Unexistance(RuntimeException e, WebRequest request) {
        ApiError apiError = new ApiError(404, "NOT_FOUND", false, e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}

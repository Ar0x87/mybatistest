package com.null01.interceptors;

import com.null01.exceptions.*;
import com.null01.wrappers.Errorer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    protected /*ResponseEntity<Object>*/ Errorer AlreadyExist(RuntimeException e) {
        Errorer errorer = new Errorer(412, "EXISTENCE_CONFLICT",  false, e.getMessage());
        //return new ResponseEntity<>(errorer, HttpStatus.I_AM_A_TEAPOT);
        return errorer;
    }

    @ExceptionHandler(EmptyBodyException.class)
    protected /*ResponseEntity<Object>*/ Errorer EmptyBody(RuntimeException e) {
        Errorer errorer = new Errorer(406, "EMPTY_REQUEST_EXCEPTION", false, e.getMessage());
        //return new ResponseEntity<>(errorer, HttpStatus.BAD_REQUEST);
        return errorer;
    }

    @ExceptionHandler(MisstargetException.class)
    protected /*ResponseEntity<Object>*/ Errorer Misstarget(RuntimeException e) {
        Errorer errorer = new Errorer(416, "MISSTARGETING", false, e.getMessage());
        //return new ResponseEntity<>(errorer,HttpStatus.BAD_REQUEST);
        return errorer;
    }

    @ExceptionHandler({UnexistanceException.class, NumberFormatException.class})
    protected /*ResponseEntity<Object>*/ Errorer Unexistance(RuntimeException e) {
        Errorer errorer = new Errorer(404, "NOT_FOUND", false, e.getMessage());
        //return new ResponseEntity<>(errorer, HttpStatus.NOT_FOUND);
        return errorer;
    }

    @ExceptionHandler({ConnectionLossException.class, SQLException.class})
    protected /*ResponseEntity<Object>*/ Errorer ConnectionLoss(SQLException e) {
        Errorer errorer = new Errorer(500, "INTERNAL_SERVER_ERROR", false, e.getMessage());
        //return new ResponseEntity<>(errorer, HttpStatus.INTERNAL_SERVER_ERROR);
        return errorer;
    }

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> Invalidity(MethodArgumentNotValidException e) {
        Pattern p1 = Pattern.compile("(Invalid{1})(\\D*)(\\Q]]\\E)");
        Pattern p2 = Pattern.compile("(Invalid {1})(\\D{2,9})(\\Q]]\\E)(\\D+)(Invalid{1})(\\D*)(\\Q]]\\E)");
        Pattern p3 = Pattern.compile("(Invalid {1})(\\D{2,9})(\\Q]]\\E)(\\D+)(Invalid{1})(\\D{2,9})(\\Q]]\\E)(\\D+)(Invalid{1})(\\D*)(\\Q]]\\E)");
        Pattern p4 = Pattern.compile("(Empty{1})(\\D*)(\\Q}]]\\E)");
        Matcher m1 = p1.matcher(e.getMessage());
        Matcher m2 = p2.matcher(e.getMessage());
        Matcher m3 = p3.matcher(e.getMessage());
        Matcher m4 = p4.matcher(e.getMessage());
        String descript = null;
        if (m1.find()) {
            descript = m1.group(1) + m1.group(2);
        }
        if (m2.find()) {
            descript = m2.group(1) + m2.group(2) + ", " + m2.group(5) + m2.group(6);
        }
        if (m3.find()) {
            descript = m3.group(1) + m3.group(2) + ", " + m3.group(5) + m3.group(6) + ", " + m3.group(9) + m3.group(10);
        }
        if (m4.find()) {
            descript = m4.group(1) + m4.group(2);
        }
        Errorer errorer = new Errorer(400, "BAD_REQUEST", false, descript);
        return new ResponseEntity<>(errorer, HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(/*MethodArgumentNotValidException.class*/BindException.class)
    protected /*ResponseEntity<Object>*/ Errorer handleValidationExceptions(/*MethodArgumentNotValidException*/BindException e) {
        String descript = "";
        ArrayList<String> notVals = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            notVals.add(errorMessage);
        });
        for (String er : notVals) {
            if (er != null && !er.equals("") && er != "null") {
                descript = descript + er + " ";
            }
        }
        Errorer errorer = new Errorer(400, "BAD_REQUEST", false, descript);
        //return new ResponseEntity<>(errorer, HttpStatus.BAD_REQUEST);
        return errorer;
    }

    /*@ExceptionHandler(BindException.class)
    protected Errorer handleBindExeptions(BindException e) {
        Errorer errorer = new Errorer(404, "BAD_REQUEST", false, e.getMessage());
        //return new ResponseEntity<>(errorer, HttpStatus.BAD_REQUEST);
        return errorer;
    }*/

    //Debug

    /*@ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> HandleException(Exception e) {
        Errorer errorer = new Errorer(600, "UNHANDLED", false, e.getMessage());
        return new ResponseEntity<>(errorer, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

}

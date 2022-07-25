package com.null01.exceptions;

import java.sql.SQLException;

public class ConnectionLossException extends SQLException {
    public ConnectionLossException(String message){
        super(message);
    }
}

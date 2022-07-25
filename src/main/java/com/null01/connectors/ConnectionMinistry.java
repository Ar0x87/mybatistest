package com.null01.connectors;

import java.sql.*;

import com.null01.configuration.Properties;
import com.null01.exceptions.ConnectionLossException;

public class ConnectionMinistry {
    private boolean connectionSuccess;
    private Connection connectionInstallation;

    public boolean isConnectionSuccess() throws SQLException {
            Properties proper = new Properties();
            connectionInstallation = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword());
            if (connectionInstallation != null){
                connectionSuccess = true;
            } else {
                connectionSuccess = false;
                throw new ConnectionLossException("Failed SQL server connection");
            }
            return connectionSuccess;
    }


    public Connection connect() {
        return connectionInstallation;
    }
}
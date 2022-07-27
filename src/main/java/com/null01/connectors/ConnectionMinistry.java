package com.null01.connectors;

import java.sql.*;

import com.null01.configuration.Properties;
import com.null01.exceptions.ConnectionLossException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConnectionMinistry {

    @Qualifier("proper")
    @Autowired
    private final Properties proper;
    private boolean connectionSuccess;
    private Connection connectionInstallation;

    public boolean isConnectionSuccess() throws SQLException {
            connectionInstallation = DriverManager.getConnection(proper.getUrl(), proper.getUsername(), proper.getPassword());
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
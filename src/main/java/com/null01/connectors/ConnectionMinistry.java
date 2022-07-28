package com.null01.connectors;

import java.sql.*;

import com.null01.configuration.Properties;
import com.null01.exceptions.ConnectionLossException;
import com.null01.mappers.UniversalDataProcessors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    static final Logger log = LoggerFactory.getLogger(ConnectionMinistry.class);

    public boolean isConnectionSuccess() throws SQLException {
        log.debug("*-----------------------------------------------------------------------------------------*");
        connectionInstallation = DriverManager.getConnection(proper.getUrl(), proper.getUsername(), proper.getPassword());
            log.debug("Checking connection ability...");
            if (connectionInstallation != null){
                connectionSuccess = true;
                log.debug("Connection successful installed.");
            } else {
                log.error("Failed connection with SQL server.");
                connectionSuccess = false;
                throw new ConnectionLossException("Failed SQL server connection");
            }
            return connectionSuccess;
    }

    public Connection connect() {
        return connectionInstallation;
    }

}
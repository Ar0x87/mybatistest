package com.null01.configuration;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.datasource")
@Component("proper")
@Data
public class Properties {

    private String url;
    private String username;
    private String password;

}
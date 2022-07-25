package com.null01.configuration;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Properties {
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "postgres";
    private String password = "12439524";

}

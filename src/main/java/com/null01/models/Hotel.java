package com.null01.models;

import lombok.Data;

/**
 * Model of the object in database.
 * Provides the comprehension between SQL and Java.
 *
 * @Authors Vladimir, Evgeniy
 * @Version 1.0
 * @Since 09.06.2022
 */
@Data
public class Hotel {

    private Integer id;
    private String hotelname;
    private String address;

}
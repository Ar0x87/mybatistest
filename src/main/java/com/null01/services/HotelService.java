package com.null01.services;

import com.null01.models.Hotel;
import com.null01.models.RequestStructure;

import java.util.ArrayList;

/**
 * Service interface provides connection between controller layer and data layer.
 *
 * @Authors Vladimir, Evgeniy
 * @Version 1.0
 * @Since 09.06.2022
 */

public interface HotelService {

    ArrayList<Hotel> getAll();

    ArrayList<Hotel> getByName(String name);

    Integer postJ(RequestStructure reqBod);

    ArrayList<Hotel> putJ(Integer id, RequestStructure reqBod);

}

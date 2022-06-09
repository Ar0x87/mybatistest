package com.null01.services;

import com.null01.mappers.HotelMapper;
import com.null01.models.Hotel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of service for more modularity of project.
 * Another data layer between controller and database
 *
 * @Authors Vladimir, Evgeniy
 * @Version 1.0
 * @Since 09.06.2022
 */

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelmapper;

    public final ArrayList<Hotel> getAll() {
        return hotelmapper.getAll();
    }

    public final ArrayList<Hotel> getByName(String name) {
        return hotelmapper.getByName('%' + name + '%');
    }
}

package com.null01.services;

import com.null01.mappers.HotelMapper;
import com.null01.models.Hotel;
import java.util.ArrayList;
import java.util.Map;

import com.null01.models.RequestStructure;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private final HotelMapper hotelmapper;

    public final ArrayList<Hotel> getAll() {
        return hotelmapper.getAll();
    }

    public final ArrayList<Hotel> getByName(String name) {
        return hotelmapper.getByName('%' + name + '%');
    }

    public final Integer postJ(@NonNull RequestStructure reqBod){

        return hotelmapper.postJ(Map.of("hotelname", reqBod.hotelname, "address", reqBod.address));
    }
}

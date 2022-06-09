package com.null01.services;

import com.null01.mappers.HotelMapper;
import com.null01.models.Hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelMapper hotelmapper;

    public final ArrayList<Hotel> getAll(){
        return hotelmapper.getAll();
    }

    public final ArrayList<Hotel> getByName(String name){
        return hotelmapper.getByName('%' +name +'%');
    }
}

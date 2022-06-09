package com.null01.services;

import com.null01.models.Hotel;

import java.util.ArrayList;

public interface HotelService {

    ArrayList<Hotel> getAll();

    ArrayList<Hotel> getByName(String name);

}

package com.null01;


import java.util.ArrayList;

interface HotelService {
    public ArrayList<Hotel> getAll();

    public ArrayList<Hotel> getByName(String name);
}

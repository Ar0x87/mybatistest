package com.null01;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
interface HotelMapper {

    public ArrayList<Hotel> getAll();

    public ArrayList<Hotel> getByName(String name);
}

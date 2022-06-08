package com.null01;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
interface HotelMapper {

    public List<Hotel> getAll();

    public List<Hotel> getByName(String name);
}

package com.null01;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {
    default List<Hotel> getAll() {
        return null;
    }
}

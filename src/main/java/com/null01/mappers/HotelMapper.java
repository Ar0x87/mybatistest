package com.null01.mappers;

import com.null01.models.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface HotelMapper {

    ArrayList<Hotel> getAll();

    ArrayList<Hotel> getByName(String name);
}

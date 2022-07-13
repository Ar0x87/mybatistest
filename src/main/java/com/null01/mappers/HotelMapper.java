package com.null01.mappers;

import com.null01.models.Hotel;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper interface that allows work with SQL.
 * Must have an XML analog which contain the syntax of SQL queries,
 * bounded with methods from Mapper.java by id's.
 * Required to be contained in src.main.java."main_catalog".mappers.
 *
 * @Authors Vladimir, Evgeniy
 * @Version 1.0
 * @Since 09.06.2022
 */

@Mapper
@Repository
public interface HotelMapper {

    ArrayList<Hotel> getAll();

    ArrayList<Hotel> getByName(String name);

    ArrayList<Hotel> getByNameForDel(String name);

    Integer postJ(Map<String, String> jSS);

    Integer putJ(Map<String, String> jSS);

    Integer checkIdExistance(Integer cie);

    void poster(Map<String, String> jSS);

    void puter(Map<String, String> jSS);

    void delter(Integer x);

    Integer delJ(Integer id);

    ArrayList<Integer> delJ(String name);

    String getHotelnameById(Integer id);

    String getAddressById(Integer id);

}

package com.null01.services;

import com.null01.mappers.HotelMapper;
import com.null01.models.Hotel;
import java.util.ArrayList;
import java.util.Map;

import com.null01.models.RequestStructure;
import com.null01.models.RequestStructureFullLine;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Integer.valueOf;

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

    //Main Logic of "additionalMethodLogic" branch

    public final Integer postJ(RequestStructure reqBod) {
        Integer rslt;
        if (getIdByName(reqBod.hotelname) == null) {
            hotelmapper.poster(Map.of("hotelname", reqBod.hotelname, "address", reqBod.address));
            rslt = getIdByName(reqBod.hotelname);
        } else {
            rslt = -1;
        }
        return rslt;
    }

    public final Integer putJ(RequestStructureFullLine reqLin) {
        Integer rslt;
        if (checkIdExistance(valueOf(reqLin.id)) != null) {
            hotelmapper.puter(Map.of("id", reqLin.id, "hotelname", reqLin.hotelname, "address", reqLin.address));
            rslt = valueOf(reqLin.id);
        } else {
            rslt = -1;
        }
        return rslt;
    }

    //Auxiliary Methods

    public final Integer getIdByName(String name) {
        return hotelmapper.getIdByName(name);
    }

    public final Integer checkIdExistance(Integer cie) {
        return hotelmapper.checkIdExistance(cie);
    }

    //SQL Alternators

    public final void poster(RequestStructure reqBod) {
    }

    public final void puter(RequestStructureFullLine reqBod) {
    }

}

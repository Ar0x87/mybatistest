package com.null01.services;

import com.null01.Exeptions.AlreadyExistExeption;
import com.null01.mappers.HotelMapper;
import com.null01.models.Hotel;

import java.util.ArrayList;
import java.util.Map;

import com.null01.models.RequestStructure;
import com.null01.models.RequestStructureFullLine;
import lombok.RequiredArgsConstructor;
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
        return hotelmapper.getByName(name);
    }

    //Main Logic of "additionalMethodLogic" branch

    public final Integer postJ(RequestStructure reqBod) throws AlreadyExistExeption  {
        Integer rslt = getIdByName(reqBod.getHotelname());
        if (rslt != null){
            throw new AlreadyExistExeption("Already exist");
        } else {
            hotelmapper.poster(Map.of("hotelname", reqBod.getHotelname(), "address", reqBod.getAddress()));
            rslt = getIdByName(reqBod.getHotelname());
        }
        return rslt;
    }

   public final Integer putJ(RequestStructureFullLine reqLin) throws NullPointerException{
        Integer rslt = checkIdExistance(valueOf(reqLin.getId()));
        if (rslt == null) {
            throw new NullPointerException("There is no such ID");
        } else {
            hotelmapper.puter(Map.of("id", reqLin.getId(), "hotelname", reqLin.getHotelname(), "address", reqLin.getAddress()));
            rslt = valueOf(reqLin.getId());
        }
        return rslt;
   }

   public final Integer delJ(Integer id) throws NullPointerException{
        Integer rslt = checkIdExistance(id);
        if (rslt == null) {
            throw new NullPointerException("There is no such ID");
        } else {
            hotelmapper.delter(rslt);
        }
        return rslt;
   }

   public final Integer delJ(String name) throws NullPointerException{
        Integer rslt = getIdByName(name);
        if (rslt == null) {
            throw new NullPointerException("There is no such Hotel");
        } else {
            hotelmapper.delter(rslt);
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

   public final void delter(Integer x){
   }

}

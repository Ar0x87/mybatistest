package com.null01.services;

import com.null01.Exeptions.AlreadyExistExeption;
import com.null01.Exeptions.MisstargetException;
import com.null01.Exeptions.EmptyBodyException;
import com.null01.Exeptions.UnexistanceExeption;
import com.null01.mappers.HotelMapper;
import com.null01.models.Hotel;

import java.util.ArrayList;
import java.util.Map;

import com.null01.Requests.RequestStructure;
import com.null01.Requests.RequestStructureFullLine;
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

    public final Integer postJ(RequestStructure reqBod) throws AlreadyExistExeption {
        ArrayList<Integer> rslt = getIdByName(reqBod.getHotelname());
        if (!rslt.isEmpty()){
            throw new AlreadyExistExeption("Already exist");
        } else {
            hotelmapper.poster(Map.of("hotelname", reqBod.getHotelname(), "address", reqBod.getAddress()));
            rslt = getIdByName(reqBod.getHotelname());
        }
        return rslt.get(0);
    }

   public final Integer putJ(RequestStructureFullLine reqLin) throws UnexistanceExeption {
        Integer rslt = checkIdExistance(valueOf(reqLin.getId()));
        if (rslt == null) {
            throw new UnexistanceExeption("There is no such ID");
        } else {
            hotelmapper.puter(Map.of("id", reqLin.getId(), "hotelname", reqLin.getHotelname(), "address", reqLin.getAddress()));
            rslt = valueOf(reqLin.getId());
        }
        return rslt;
   }

   public final Integer delJ(Integer id) throws UnexistanceExeption {
        Integer rslt = checkIdExistance(id);
        if (rslt == null) {
            throw new UnexistanceExeption("There is no such ID");
        } else {
            hotelmapper.delter(rslt);
        }
        return rslt;
   }

   public final ArrayList<Integer> delJ(String name) throws UnexistanceExeption {
        ArrayList<Integer> rslt = getIdByName(name);
        if (rslt.isEmpty()) {
            throw new UnexistanceExeption("There is no such Hotel");
        } else {
            for (Integer x: rslt ){
                hotelmapper.delter(x);
            }
        }
        return rslt;
   }

   public final Integer patJ(RequestStructureFullLine reqLin) throws MisstargetException, EmptyBodyException, NullPointerException {
        Integer rslt = checkIdExistance(valueOf(reqLin.getId()));
        if (rslt == null) {
            throw new MisstargetException("There is no such entry");
        } else {
            if (reqLin.getHotelname() == null && reqLin.getAddress() == null) {
                throw new EmptyBodyException("Empty request");
            } else {
                if (reqLin.getHotelname() == null) {
                    hotelmapper.puter(Map.of("id", reqLin.getId(), "hotelname", getHotelnameById(valueOf(reqLin.getId())), "address", reqLin.getAddress()));
                }
                if (reqLin.getAddress() == null) {
                    hotelmapper.puter(Map.of("id", reqLin.getId(), "hotelname", reqLin.getHotelname(), "address", getAddressById(valueOf(reqLin.getId()))));
                }
                if (reqLin.getHotelname() != null && reqLin.getAddress() != null) {
                    hotelmapper.puter(Map.of("id", reqLin.getId(), "hotelname", reqLin.getHotelname(), "address", reqLin.getAddress()));
                }
            }
        }
        return rslt;
   }

    //Auxiliary Methods

   public final ArrayList<Integer> getIdByName(String name) {
       return caster(hotelmapper.getByNameForDel(name));
   }

   public final Integer checkIdExistance(Integer cie) {
       return hotelmapper.checkIdExistance(cie);
   }

   public final String getHotelnameById(Integer id) {
        return hotelmapper.getHotelnameById(id);
   }

   public final String getAddressById(Integer id) {
        return hotelmapper.getAddressById(id);
   }

    public final ArrayList<Hotel> getByNameForDel(String name) {
        return hotelmapper.getByNameForDel(name);
    }


    public final ArrayList<Integer> caster(ArrayList<Hotel> cst) {
        ArrayList<Integer> rslt = new ArrayList<>();
        for (Hotel x: cst) {
            rslt.add(x.getId());
        }
        return rslt;
   }

    //SQL Alternators

   public final void poster(RequestStructure reqBod) {
   }

   public final void puter(RequestStructureFullLine reqBod) {
   }

   public final void delter(Integer x) {
   }

}

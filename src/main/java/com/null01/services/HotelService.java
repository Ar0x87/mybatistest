package com.null01.services;

import com.null01.Exeptions.AlreadyExistExeption;
import com.null01.Exeptions.EmptyBodyException;
import com.null01.models.Hotel;
import com.null01.models.RequestStructure;
import com.null01.models.RequestStructureFullLine;
import com.null01.models.RequestStructurePartial;

import java.util.ArrayList;

/**
 * Service interface provides connection between controller layer and data layer.
 *
 * @Authors Vladimir, Evgeniy
 * @Version 1.0
 * @Since 09.06.2022
 */

public interface HotelService {

    ArrayList<Hotel> getAll();

    ArrayList<Hotel> getByName(String name);

    Integer postJ(RequestStructure reqBod) throws AlreadyExistExeption;

    Integer getIdByName(String name) throws AlreadyExistExeption;

    void poster(RequestStructure reqBod);

    void puter(RequestStructureFullLine reqLin);

    Integer checkIdExistance(Integer cie);

    Integer putJ(RequestStructureFullLine reqLin);

    void delter(Integer x);

    Integer delJ(Integer id);

    Integer delJ(String name);

    void alterHotel(RequestStructureFullLine reqLin);

    void alterAddress(RequestStructureFullLine reqLin);

    Integer patJ(RequestStructureFullLine reqLin) throws EmptyBodyException;

}

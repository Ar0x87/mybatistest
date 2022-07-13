package com.null01.services;

import com.null01.Exeptions.AlreadyExistExeption;
import com.null01.Exeptions.EmptyBodyException;
import com.null01.Exeptions.MisstargetException;
import com.null01.Exeptions.UnexistanceExeption;
import com.null01.models.Hotel;
import com.null01.Requests.RequestStructure;
import com.null01.Requests.RequestStructureFullLine;

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

    void poster(RequestStructure reqBod);

    void puter(RequestStructureFullLine reqLin);

    Integer checkIdExistance(Integer cie);

    Integer putJ(RequestStructureFullLine reqLin) throws UnexistanceExeption;

    void delter(Integer x);

    Integer delJ(Integer id) throws UnexistanceExeption;

    ArrayList<Integer> delJ(String name) throws UnexistanceExeption;

    Integer patJ(RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException;

    String getHotelnameById(Integer id);

    String getAddressById(Integer id);

}

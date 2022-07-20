package com.null01.services;

import com.null01.exceptions.AlreadyExistException;
import com.null01.exceptions.EmptyBodyException;
import com.null01.exceptions.MisstargetException;
import com.null01.exceptions.UnexistanceException;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.RequestStructureFullLine;

import java.util.ArrayList;

/**
 * Service interface provides connection between controller layer and data layer.
 *
 * @Authors Vladimir, Evgeniy
 * @Version 1.0
 * @Since 09.06.2022
 */

public interface HotelService {

    //Simple getters
    ArrayList<Hotel> getAll();
    ArrayList<Hotel> getByName(String name);

    //Main logic methods
    Integer postJ(RequestStructure reqBod) throws AlreadyExistException;
    Integer putJ(RequestStructureFullLine reqLin) throws UnexistanceException;
    Integer delJ(Integer id) throws UnexistanceException;
    ArrayList<Integer> delJ(String name) throws UnexistanceException;
    Integer patJ(RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException;

    //Auxiliary methods
    ArrayList<Hotel> getHotelMapByName(String name);
    Integer checkIdExistance(Integer cie);
    String getHotelnameById(Integer id);
    String getAddressById(Integer id);
    ArrayList<Integer> getIdByName(String name);
    ArrayList<Integer> caster(ArrayList<Hotel> cst);

    //SQL alternators
    void poster(RequestStructure reqBod);
    void puter(RequestStructureFullLine reqLin);
    void delter(Integer x);

}

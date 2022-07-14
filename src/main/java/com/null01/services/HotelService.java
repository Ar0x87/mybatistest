package com.null01.services;

import com.null01.Exceptions.AlreadyExistException;
import com.null01.Exceptions.EmptyBodyException;
import com.null01.Exceptions.MisstargetException;
import com.null01.Exceptions.UnexistanceException;
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

    ArrayList<Hotel> getHotelMapByName(String name);

    Integer postJ(RequestStructure reqBod) throws AlreadyExistException;

    void poster(RequestStructure reqBod);

    void puter(RequestStructureFullLine reqLin);

    Integer checkIdExistance(Integer cie);

    Integer putJ(RequestStructureFullLine reqLin) throws UnexistanceException;

    void delter(Integer x);

    Integer delJ(Integer id) throws UnexistanceException;

    ArrayList<Integer> delJ(String name) throws UnexistanceException;

    Integer patJ(RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException;

    String getHotelnameById(Integer id);

    String getAddressById(Integer id);

}

package com.null01.services;

import com.null01.exceptions.*;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.StructureForPatch;
import com.null01.requests.StructureForPut;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Service interface provides connection between controller layer and data layer.
 *
 * @Authors Vladimir, Evgeniy
 * @Version 1.0
 * @Since 09.06.2022
 */
@Service
public interface HotelService {

    //Simple getters
    ArrayList<Hotel> getAll() throws SQLException;
    ArrayList<Hotel> getByName(String name) throws SQLException;

    //Main logic methods
    Integer postJ(@Valid RequestStructure reqBod) throws AlreadyExistException, SQLException;
    Integer putJ(@Valid StructureForPut reqLin) throws UnexistanceException, SQLException;
    Integer delJ(Integer id) throws UnexistanceException, SQLException;
    ArrayList delJar(String name) throws UnexistanceException, SQLException;
    Integer patJ(@Valid StructureForPatch reqLin) throws EmptyBodyException, MisstargetException, SQLException;

    //Auxiliary methods
    ArrayList<Hotel> getHotelMapByName(String name) throws SQLException;
    Integer checkIdExistance(Integer cie) throws SQLException;
    String getHotelnameById(Integer id) throws SQLException;
    String getAddressById(Integer id) throws SQLException;
    ArrayList<Integer> getIdByName(String name) throws SQLException;
    ArrayList<Integer> caster(ArrayList<Hotel> cst);

    /*//SQL alternators
    void poster(RequestStructure reqBod);
    void puter(RequestStructureFullLine reqLin);
    void delter(Integer x);*/

}
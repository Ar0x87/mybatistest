package com.null01.services;

import com.null01.exceptions.AlreadyExistException;
import com.null01.exceptions.EmptyBodyException;
import com.null01.exceptions.MisstargetException;
import com.null01.exceptions.UnexistanceException;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.RequestStructureFullLine;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Primary
@RequiredArgsConstructor
@Service
public class HotelServiceJDBC implements HotelService {

    @Override
    public ArrayList<Hotel> getAll() {
        String sql = "SELECT id, hotelname, address FROM hotel";
        ArrayList<Hotel> hotels = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "12439524")) {
            try (Statement sttmnt = con.createStatement()) {
                try (ResultSet resultSet = sttmnt.executeQuery(sql)) {
                    while (resultSet.next()) {
                        Hotel hotel = new Hotel();
                        hotel.setId(resultSet.getInt("id"));
                        hotel.setHotelName(resultSet.getString("hotelname"));
                        hotel.setAddress(resultSet.getString("address"));
                        hotels.add(hotel);
                    }
                    return hotels;
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return hotels;
    }

    @Override
    public ArrayList<Hotel> getByName(String name) {
        return null;
    }

    @Override
    public ArrayList<Hotel> getHotelMapByName(String name) {
        return null;
    }

    @Override
    public Integer postJ(RequestStructure reqBod) throws AlreadyExistException {
        return null;
    }

    @Override
    public void poster(RequestStructure reqBod) {

    }

    @Override
    public void puter(RequestStructureFullLine reqLin) {

    }

    @Override
    public Integer checkIdExistance(Integer cie) {
        return null;
    }

    @Override
    public Integer putJ(RequestStructureFullLine reqLin) throws UnexistanceException {
        return null;
    }

    @Override
    public void delter(Integer x) {

    }

    @Override
    public Integer delJ(Integer id) throws UnexistanceException {
        return null;
    }

    @Override
    public ArrayList<Integer> delJ(String name) throws UnexistanceException {
        return null;
    }

    @Override
    public Integer patJ(RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException {
        return null;
    }

    @Override
    public String getHotelnameById(Integer id) {
        return null;
    }

    @Override
    public String getAddressById(Integer id) {
        return null;
    }
}

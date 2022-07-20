package com.null01.services;

import com.null01.configuration.Properties;
import com.null01.exceptions.AlreadyExistException;
import com.null01.exceptions.EmptyBodyException;
import com.null01.exceptions.MisstargetException;
import com.null01.exceptions.UnexistanceException;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.RequestStructureFullLine;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.Integer.valueOf;

@Primary
@RequiredArgsConstructor
@Service
public class HotelServiceJDBC implements HotelService {

    Properties proper = new Properties();

    //Simple getters

    @Override
    public ArrayList<Hotel> getAll() {
        String sql = "SELECT id, hotelname, address FROM hotel";
        ArrayList<Hotel> hotels = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword())) {
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
        } catch (
                SQLException sqle) {
            sqle.printStackTrace();
        }
        return hotels;
    }

    @Override
    public ArrayList<Hotel> getByName(String name) {
        String sql = "SELECT id, hotelname, address FROM hotel WHERE lower(hotelname) LIKE lower(concat('%', ?, '%'))";
        ArrayList<Hotel> hotels = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword()); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getInt("id"));
                    hotel.setHotelName(rs.getString("hotelname"));
                    hotel.setAddress(rs.getString("address"));
                    hotels.add(hotel);
                }
                return hotels;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return hotels;
    }

    //Main Logic of "additionalMethodLogic" branch

    @Override
    public Integer postJ(RequestStructure reqBod) throws AlreadyExistException {
        ArrayList<Integer> stp = getIdByName(reqBod.getHotelname());
        Integer rslt;
        if (stp.isEmpty()) {
            poster(reqBod);
            stp = getIdByName(reqBod.getHotelname());
            rslt = stp.get(0);
        } else {
            throw new AlreadyExistException("Entity with same name already exist");
        }
        return rslt;
    }

    @Override
    public Integer putJ(RequestStructureFullLine reqLin) throws UnexistanceException {
        Integer rslt;
        if (reqLin.getId() != null && !reqLin.getId().equals("")) {
            if (checkIdExistance(Integer.parseInt(reqLin.getId())) != null && checkIdExistance(Integer.parseInt(reqLin.getId())) > 0) {
                puter(reqLin);
                rslt = Integer.parseInt(reqLin.getId());
            } else {
                throw new UnexistanceException("There is no such ID");
            }
        } else {
            throw new NumberFormatException("Bad ID in request");
        }
        return rslt;
    }

    @Override
    public Integer delJ(Integer id) throws UnexistanceException {
        String sql = "SELECT id FROM hotel WHERE id = ?";
        Integer result = null;

        try (Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword()); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result = rs.getInt("id");
                    delter(rs.getInt("id"));
                }
                if (result == null || result == 0) {
                    throw new UnexistanceException("There is no such ID");
                } else {
                    return result;
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Integer> delJ(String name) throws UnexistanceException {
        String sql = "SELECT id FROM hotel WHERE hotelname = ?";
        ArrayList<Integer> result = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword()); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(rs.getInt("id"));
                    delter(rs.getInt("id"));
                }
                if (result.isEmpty()) {
                    throw new UnexistanceException("There is no such Hotel");
                } else {
                    return result;
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return result;
    }

    @Override
    public Integer patJ(RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException {
        Integer rslt;
        if (reqLin.getId() == null || reqLin.getId().equals("") || checkIdExistance(Integer.parseInt(reqLin.getId())) == null) {
            throw new MisstargetException("There is no such entry");
        } else {
            if (reqLin.getHotelname() == null && reqLin.getAddress() == null) {
                throw new EmptyBodyException("Sending request is empty");
            } else {
                RequestStructureFullLine rsfl = reqLin;
                if (reqLin.getHotelname() == null) {
                    rsfl.setHotelname(getHotelnameById(valueOf(reqLin.getId())));
                    puter(rsfl);
                }
                if (reqLin.getAddress() == null) {
                    rsfl.setHotelname(getAddressById(valueOf(reqLin.getId())));
                    puter(rsfl);
                }
                if (reqLin.getHotelname() != null && reqLin.getAddress() != null) {
                    puter(reqLin);
                }
                rslt = checkIdExistance(Integer.parseInt(reqLin.getId()));
            }
        }
        return rslt;
    }

    //Auxiliary methods

    @Override
    public ArrayList<Hotel> getHotelMapByName(String name) {
        String sql = "SELECT id, hotelname, address FROM hotel WHERE hotelname = ?";
        ArrayList<Hotel> hotels = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword()); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setId(rs.getInt("id"));
                    hotel.setHotelName(rs.getString("hotelname"));
                    hotel.setAddress(rs.getString("address"));
                    hotels.add(hotel);
                }
                return hotels;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return hotels;
    }

    @Override
    public Integer checkIdExistance(Integer cie) {
        String sql = "SELECT id FROM hotel WHERE id = ?";
        Integer result = null;
        try (Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword()); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cie);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result = rs.getInt("id");
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getHotelnameById(Integer id) {
        String sql = "SELECT hotelname FROM hotel WHERE id = ?";
        String result = null;
        try (Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword()); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result = rs.getString("hotelname");
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getAddressById(Integer id) {
        String sql = "SELECT hotelname FROM hotel WHERE id = ?";
        String result = null;
        try (Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword()); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result = rs.getString("address");
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Integer> getIdByName(String name) {
        return caster(getHotelMapByName(name));
    }

    @Override
    public ArrayList<Integer> caster(ArrayList<Hotel> cst) {
            ArrayList<Integer> rslt = new ArrayList<>();
            for (Hotel x: cst) {
                rslt.add(x.getId());
            }
            return rslt;
    }

    //SQL Alternators

    @Override
    @SneakyThrows
    public void poster(RequestStructure reqBod) {
        String sql = "SELECT setval('hotel_id_seq', (SELECT max(id) FROM hotel));" +
        "INSERT INTO hotel(id, hotelname, address) VALUES (nextval('hotel_id_seq'), '"+ reqBod.getHotelname() +"' , '"+ reqBod.getAddress() +"' );";
        Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword());
        PreparedStatement ps = con.prepareStatement(sql);
        try {
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Poster worked normally");
        }
    }

    @Override
    @SneakyThrows
    public void puter(RequestStructureFullLine reqLin) {
        String sql = "UPDATE hotel SET hotelname = '" +reqLin.getHotelname()+ "', address = '" +reqLin.getAddress()+ "' WHERE id = '" +Integer.parseInt(reqLin.getId())+ "';";
        Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword());
        PreparedStatement ps = con.prepareStatement(sql);
        try {
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Puter worked normally");
        }
    }

    @Override
    @SneakyThrows
    public void delter(Integer x) {
        String sql = "DELETE FROM hotel WHERE id = ?";
        Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword());
        PreparedStatement ps = con.prepareStatement(sql);
        try {
            ps.setInt(1, x);
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Deleter worked normally");
        }
    }

}


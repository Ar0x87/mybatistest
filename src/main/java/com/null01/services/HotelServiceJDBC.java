package com.null01.services;

import com.null01.configuration.Properties;
import com.null01.connectors.ConnectionMinistry;
import com.null01.exceptions.*;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.RequestStructureFullLine;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Consumer;

import static java.lang.Integer.valueOf;

@Primary
@RequiredArgsConstructor
@Service
public class HotelServiceJDBC implements HotelService {

    //Simple getters

    @Override
    public ArrayList<Hotel> getAll() throws SQLException {
        String sql = "SELECT id, hotelname, address FROM hotel ORDER BY id";
        return RetrieveData(sql);
    }

    @Override
    public ArrayList<Hotel> getByName(String name) throws SQLException {
        String sql = "SELECT id, hotelname, address FROM hotel WHERE lower(hotelname) LIKE lower(concat('%', ?, '%'))";
        ArrayList<Hotel> result = RetrieveData(sql, name);
        if (result.isEmpty()) {
            throw new UnexistanceException("There is no such hotel");
        } else {
            return result;
        }
    }

    //Main Logic of "additionalMethodLogic" branch

    @Override
    public Integer postJ(RequestStructure reqBod) throws AlreadyExistException, SQLException {
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
    public Integer putJ(RequestStructureFullLine reqLin) throws UnexistanceException, SQLException {
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
    public Integer delJ(Integer id) throws UnexistanceException, SQLException, InvocationTargetException, IllegalAccessException {
        String sql = "SELECT id FROM hotel WHERE id = ?";
        ArrayList<Integer> arrayList = RetrieveData(sql, id, new HotelServiceJDBC()::delter);
        if (arrayList.isEmpty()) {
            throw new UnexistanceException("Nothing to delete by this id");
        } else {
            return arrayList.get(0);
        }
    }

    @Override
    public ArrayList<Integer> delJ(String name) throws UnexistanceException, SQLException, IllegalAccessException, InvocationTargetException {
        String sql = "SELECT id FROM hotel WHERE hotelname = ?";
        ArrayList arrayList = RetrieveData(sql, name, new HotelServiceJDBC()::delter);
        if (arrayList.isEmpty()) {
            throw new UnexistanceException("Nothing to delete by this name");
        } else {
            return arrayList;
        }
    }

    @Override
    public Integer patJ(RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException, SQLException {
        Integer rslt;
        if (reqLin.getId() == null || reqLin.getId().equals("") || checkIdExistance(valueOf(reqLin.getId())) == null) {
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
                    rsfl.setAddress(getAddressById(valueOf(reqLin.getId())));
                    puter(rsfl);
                }
                if (reqLin.getHotelname() != null && reqLin.getAddress() != null) {
                    puter(reqLin);
                }
                rslt = checkIdExistance(valueOf(reqLin.getId()));
            }
        }
        return rslt;
    }

    //Auxiliary methods

    @Override
    public ArrayList<Hotel> getHotelMapByName(String name) throws SQLException {
        String sql = "SELECT id, hotelname, address FROM hotel WHERE hotelname = ?";
        return RetrieveData(sql, name);
    }

    @Override
    public Integer checkIdExistance(Integer cie) throws SQLException {
        String sql = "SELECT id, hotelname, address FROM hotel WHERE id = ?";
        Integer result;
        if (RetrieveData(sql,cie).size() == 1) {
            Hotel receiving = (Hotel) RetrieveData(sql, cie).get(0);
            result = receiving.getId();
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public String getHotelnameById(Integer id) {
        String sql = "SELECT hotelname FROM hotel WHERE id = ?";
        String result = null;
        Properties proper = new Properties();
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
        String sql = "SELECT address FROM hotel WHERE id = ?";
        String result = null;
        Properties proper = new Properties();
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
    public ArrayList<Integer> getIdByName(String name) throws SQLException {
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
        Properties proper = new Properties();
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
        Properties proper = new Properties();
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
        Properties proper = new Properties();
        Connection con = DriverManager.getConnection(proper.getUrl(), proper.getUser(), proper.getPassword());
        PreparedStatement ps = con.prepareStatement(sql);
        try {
            ps.setInt(1, x);
            ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Deleter worked normally");
        }
    }

    //Often using or big code

    public ArrayList RetrieveData(String query) throws SQLException {
        ConnectionMinistry connectionMinistry = new ConnectionMinistry();
        ArrayList<Hotel> hotels = new ArrayList<>();
        if (connectionMinistry.isConnectionSuccess()) {
            try (Statement sttmnt = connectionMinistry.connect().createStatement()) {
                try (ResultSet resultSet = sttmnt.executeQuery(query)) {
                    while (resultSet.next()) {
                        Hotel hotel = new Hotel();
                        hotel.setId(resultSet.getInt("id"));
                        hotel.setHotelName(resultSet.getString("hotelname"));
                        hotel.setAddress(resultSet.getString("address"));
                        hotels.add(hotel);
                    }
                    return hotels;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } else {
            throw new ConnectionLossException("Connection failed due to wrong properties");
        }
        return hotels;
    }

    public ArrayList RetrieveData(String query, String name) throws SQLException {
        ConnectionMinistry connectionMinistry = new ConnectionMinistry();
        ArrayList<Hotel> hotels = new ArrayList<>();
        if (connectionMinistry.isConnectionSuccess()) {
            try (PreparedStatement ps = connectionMinistry.connect().prepareStatement(query)) {
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
        } else {
            throw new ConnectionLossException("Connection failed due to wrong properties");
        }
        return hotels;
    }

    public ArrayList RetrieveData(String query, Integer id) throws SQLException {
        ConnectionMinistry connectionMinistry = new ConnectionMinistry();
        ArrayList<Hotel> hotels = new ArrayList<>();
        if (connectionMinistry.isConnectionSuccess()) {
            try (PreparedStatement ps = connectionMinistry.connect().prepareStatement(query)) {
                ps.setInt(1, id);
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
        } else {
            throw new ConnectionLossException("Connection failed due to wrong properties");
        }
        return hotels;
    }

    public ArrayList RetrieveData(String query, String name, Consumer<Integer> method) throws SQLException, IllegalAccessException, InvocationTargetException {
        ConnectionMinistry connectionMinistry = new ConnectionMinistry();
        ArrayList<Integer> result = new ArrayList<>();
        if (connectionMinistry.isConnectionSuccess()) {
            try (PreparedStatement ps = connectionMinistry.connect().prepareStatement(query)) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(rs.getInt("id"));
                        method.accept(rs.getInt("id"));
                    }
                    return result;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } else {
            throw new ConnectionLossException("Connection failed due to wrong properties");
        }
        return result;
    }

    public ArrayList RetrieveData(String query, Integer id, Consumer<Integer> method) throws SQLException, IllegalAccessException, InvocationTargetException {
        ConnectionMinistry connectionMinistry = new ConnectionMinistry();
        ArrayList<Integer> result = new ArrayList<>();
        if (connectionMinistry.isConnectionSuccess()) {
            try (PreparedStatement ps = connectionMinistry.connect().prepareStatement(query)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(rs.getInt("id"));
                        method.accept(rs.getInt("id"));
                    }
                    return result;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } else {
            throw new ConnectionLossException("Connection failed due to wrong properties");
        }
        return result;
    }

}


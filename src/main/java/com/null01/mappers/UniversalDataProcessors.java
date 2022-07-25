package com.null01.mappers;

import com.null01.configuration.Properties;
import com.null01.connectors.ConnectionMinistry;
import com.null01.exceptions.ConnectionLossException;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.RequestStructureFullLine;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class UniversalDataProcessors {

    //Universal methods

    public ArrayList dataProcessor(String query) throws SQLException {
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

    public ArrayList dataProcessor(String query, String name) throws SQLException {
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

    public ArrayList dataProcessor(String query, Integer id) throws SQLException {
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

    public ArrayList dataProcessor(String query, String name, Consumer<Integer> method) throws SQLException, IllegalAccessException, InvocationTargetException {
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

    public ArrayList dataProcessor(String query, Integer id, Consumer<Integer> method) throws SQLException, IllegalAccessException, InvocationTargetException {
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

    //SQL alternators

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

}
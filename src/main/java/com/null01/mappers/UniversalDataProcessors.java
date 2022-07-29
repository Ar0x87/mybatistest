package com.null01.mappers;

import com.null01.connectors.ConnectionMinistry;
import com.null01.exceptions.ConnectionLossException;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.RequestStructureFullLine;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class UniversalDataProcessors {

    static final Logger log = LoggerFactory.getLogger(UniversalDataProcessors.class);

    //Universal methods

    public ArrayList dataProcessor(ConnectionMinistry cm, String query) throws SQLException {
        ArrayList<Hotel> hotels = new ArrayList<>();
        if (cm.isConnectionSuccess()) {
            log.debug("*-----------------------------------------------------------------------------------------*");
            try (Statement sttmnt = cm.connect().createStatement()) {
                try (ResultSet resultSet = sttmnt.executeQuery(query)) {
                    log.info("          id  name     address");
                    while (resultSet.next()) {
                        Hotel hotel = new Hotel();
                        hotel.setId(resultSet.getInt("id"));
                        hotel.setHotelName(resultSet.getString("hotelname"));
                        hotel.setAddress(resultSet.getString("address"));
                        hotels.add(hotel);
                        log.debug("Add entry: " +hotel.getId() +" " +hotel.getHotelName() +" " +hotel.getAddress());
                    }
                    return hotels;
                }
            } catch (SQLException sqle) {
                log.warn("SQLException handled.");
                sqle.printStackTrace();
            }
        } else {
            log.error("Connection installation failed.");
            throw new ConnectionLossException("Connection failed due to wrong properties");
        }
        log.info("Sending response.");
        return hotels;
    }

    public ArrayList dataProcessor(ConnectionMinistry cm, String query, String name) throws SQLException {
        ArrayList<Hotel> hotels = new ArrayList<>();
        if (cm.isConnectionSuccess()) {
            log.debug("*-----------------------------------------------------------------------------------------*");
            try (PreparedStatement ps = cm.connect().prepareStatement(query)) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()) {
                    log.info("          id  name     address");
                    while (rs.next()) {
                        Hotel hotel = new Hotel();
                        hotel.setId(rs.getInt("id"));
                        hotel.setHotelName(rs.getString("hotelname"));
                        hotel.setAddress(rs.getString("address"));
                        hotels.add(hotel);
                        log.debug("Add entry: " +hotel.getId() +" " +hotel.getHotelName() +" " +hotel.getAddress());

                    }
                    return hotels;
                }
            } catch (SQLException sqle) {
                log.warn("SQLException handled.");
                //sqle.printStackTrace();
            }
        } else {
            log.error("Connection installation failed.");
            throw new ConnectionLossException("Connection failed due to wrong properties");
        }
        return hotels;
    }

    public ArrayList dataProcessor(ConnectionMinistry cm, String query, Integer id) throws SQLException {
        ArrayList<Hotel> hotels = new ArrayList<>();
        if (cm.isConnectionSuccess()) {
            log.debug("*-----------------------------------------------------------------------------------------*");
            try (PreparedStatement ps = cm.connect().prepareStatement(query)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    log.info("          id  name     address");
                    while (rs.next()) {
                        Hotel hotel = new Hotel();
                        hotel.setId(rs.getInt("id"));
                        hotel.setHotelName(rs.getString("hotelname"));
                        hotel.setAddress(rs.getString("address"));
                        hotels.add(hotel);
                        log.debug("Add entry: " +hotel.getId() +" " +hotel.getHotelName() +" " +hotel.getAddress());
                    }
                    return hotels;
                }
            } catch (SQLException sqle) {
            log.warn("SQLException handled.");
                //sqle.printStackTrace();
            }
        } else {
            log.error("Connection installation failed.");
            throw new ConnectionLossException("Connection failed due to wrong properties");
        }
        return hotels;
    }

    public ArrayList dataProcessor(ConnectionMinistry cm, String query, String name, BiConsumer<ConnectionMinistry, Integer> method) throws SQLException {
        ArrayList<Integer> result = new ArrayList<>();
        if (cm.isConnectionSuccess()) {
            log.debug("*-----------------------------------------------------------------------------------------*");
            try (PreparedStatement ps = cm.connect().prepareStatement(query)) {
                ps.setString(1, name);
                try (ResultSet rs = ps.executeQuery()) {
                    log.info("          id");
                    while (rs.next()) {
                        result.add(rs.getInt("id"));
                        method.accept(cm, rs.getInt("id"));
                        log.debug("Add entry: " +rs.getInt("id"));

                    }
                    return result;
                }
            } catch (SQLException sqle) {
                log.warn("SQLException handled.");
                //sqle.printStackTrace();
            }
        } else {
            log.error("Connection installation failed.");
            throw new ConnectionLossException("Connection failed due to wrong properties");
        }
        return result;
    }

    public ArrayList dataProcessor(ConnectionMinistry cm, String query, Integer id, BiConsumer<ConnectionMinistry, Integer> method) throws SQLException {
        ArrayList<Integer> result = new ArrayList<>();
        if (cm.isConnectionSuccess()) {
            log.debug("*-----------------------------------------------------------------------------------------*");
            try (PreparedStatement ps = cm.connect().prepareStatement(query)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    log.info("          id");
                    while (rs.next()) {
                        result.add(rs.getInt("id"));
                        method.accept(cm, rs.getInt("id"));
                        log.debug("Add entry: " +rs.getInt("id"));
                    }
                    return result;
                }
            } catch (SQLException sqle) {
                log.warn("SQLException handled.");
                //sqle.printStackTrace();
            }
        } else {
            log.error("Connection installation failed.");
            throw new ConnectionLossException("Connection failed due to wrong properties");
        }
        return result;
    }

    //SQL alternators

    @SneakyThrows
    public void poster(ConnectionMinistry cm, @Valid RequestStructure reqBod) {
        String sql = "SELECT setval('hotel_id_seq', (SELECT max(id) FROM hotel));";// +
                //"INSERT INTO hotel(id, hotelname, address) VALUES (nextval('hotel_id_seq'), '"+ reqBod.getHotelname() +"' , '"+ reqBod.getAddress() +"' );";
        Connection con = cm.connect();
        PreparedStatement ps = con.prepareStatement(sql);
        log.debug("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = poster");
        log.debug("INVOLVED_METHOD_TYPE = SQL alternator");
        log.debug("INVOLVED_METHOD_BELONGING = UniversalDataProcessors");
        log.info("SQL: " +sql);
        try {
            ps.executeQuery();
        } catch (SQLException e) {
            log.debug("SQLException handled. Poster worked normally.");
            //System.out.println("Poster worked normally");
        }
    }

    @SneakyThrows
    public void puter(ConnectionMinistry cm, RequestStructureFullLine reqLin) {
        String sql = "UPDATE hotel SET hotelname = '" +reqLin.getHotelname()+ "', address = '" +reqLin.getAddress()+ "' WHERE id = '" +Integer.parseInt(reqLin.getId())+ "';";
        Connection con = cm.connect();
        PreparedStatement ps = con.prepareStatement(sql);
        log.debug("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = puter");
        log.debug("INVOLVED_METHOD_TYPE = SQL alternator");
        log.debug("INVOLVED_METHOD_BELONGING = UniversalDataProcessors");
        log.info("SQL: " +sql);
        try {
            ps.executeQuery();
        } catch (SQLException e) {
            log.debug("SQLException handled. Puter worked normally.");
            //System.out.println("Puter worked normally");
        }
    }

    @SneakyThrows
    public void delter(ConnectionMinistry cm, Integer x) {
        String sql = "DELETE FROM hotel WHERE id = ?";
        Connection con = cm.connect();
        PreparedStatement ps = con.prepareStatement(sql);
        log.debug("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = delter");
        log.debug("INVOLVED_METHOD_TYPE = SQL alternator");
        log.debug("INVOLVED_METHOD_BELONGING = UniversalDataProcessors");
        log.info("SQL: " +sql);
        try {
            ps.setInt(1, x);
            ps.executeQuery();
        } catch (SQLException e) {
            log.debug("SQLException handled. Deleter worked normally.");
            //System.out.println("Deleter worked normally");
        }
    }

}
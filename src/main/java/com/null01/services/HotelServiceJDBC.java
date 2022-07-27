package com.null01.services;

import com.null01.connectors.ConnectionMinistry;
import com.null01.exceptions.*;
import com.null01.mappers.UniversalDataProcessors;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.RequestStructureFullLine;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.Integer.valueOf;

@Primary
@RequiredArgsConstructor
@Service
public class HotelServiceJDBC implements HotelService {

    @Autowired
    private final ConnectionMinistry cm;
    static final UniversalDataProcessors udp = new UniversalDataProcessors();
    static final Logger log = LoggerFactory.getLogger(HotelServiceJDBC.class);

    //Simple getters

    @Override
    public ArrayList<Hotel> getAll() throws SQLException {
        String sql = "SELECT id, hotelname, address FROM hotel ORDER BY id";
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("JDBC using.");
        log.debug("INVOLVED_METHOD_NAME = getAll");
        log.debug("INVOLVED_METHOD_TYPE = Simple getter");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.debug("SQL: " +sql);
        log.info("Method returns ArrayList<Hotel>.");
        return udp.dataProcessor(cm, sql);
    }

    @Override
    public ArrayList<Hotel> getByName(String name) throws SQLException {
        String sql = "SELECT id, hotelname, address FROM hotel WHERE lower(hotelname) LIKE lower(concat('%', ?, '%')) ORDER BY id";
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("JDBC using.");
        log.debug("INVOLVED_METHOD_NAME = getByName");
        log.debug("INVOLVED_METHOD_TYPE = Simple getter");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.debug("SQL: " +sql);
        log.info("Method returns ArrayList<Hotel>.");
        ArrayList<Hotel> result = udp.dataProcessor(cm, sql, name);
        if (result.isEmpty()) {
            log.warn("Not found hotel with name like request.");
            log.info("Try another name.");
            throw new UnexistanceException("There is no such hotel");
        } else {
            return result;
        }
    }

    //Main Logic of "additionalMethodLogic" branch

    @Override
    public Integer postJ(RequestStructure reqBod) throws AlreadyExistException, SQLException {
        ArrayList<Integer> stp = getIdByName(reqBod.getHotelname());
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = postJ");
        log.debug("INVOLVED_METHOD_TYPE = Main logic");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.info("Method returns Integer.");
        Integer rslt;
        if (stp.isEmpty()) {
            udp.poster(cm, reqBod);
            stp = getIdByName(reqBod.getHotelname());
            rslt = stp.get(0);
        } else {
            log.warn("Entity with same name already exists.");
            log.info("Try another hotelname.");
            throw new AlreadyExistException("Entity with same name already exist");
        }
        return rslt;
    }

    @Override
    public Integer putJ(RequestStructureFullLine reqLin) throws UnexistanceException, SQLException {
        Integer rslt;
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = putJ");
        log.debug("INVOLVED_METHOD_TYPE = Main logic");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.info("Method returns Integer.");
        if (reqLin.getId() != null && !reqLin.getId().equals("")) {
            if (checkIdExistance(Integer.parseInt(reqLin.getId())) != null && checkIdExistance(Integer.parseInt(reqLin.getId())) > 0) {
                udp.puter(cm, reqLin);
                rslt = Integer.parseInt(reqLin.getId());
            } else {
                log.warn("There are no entries with such id.");
                throw new UnexistanceException("There is no such ID");
            }
        } else {
            log.error("Bad id in request.");
            throw new NumberFormatException("Bad ID in request");
        }
        return rslt;
    }

    @Override
    public Integer delJ(Integer id) throws UnexistanceException, SQLException {
        String sql = "SELECT id FROM hotel WHERE id = ?";
        ArrayList<Integer> arrayList = udp.dataProcessor(cm ,sql, id, udp::delter);
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = delJ");
        log.debug("INVOLVED_METHOD_TYPE = Main logic");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.debug("SQL: " +sql);
        log.info("Method returns Integer.");
        if (arrayList.isEmpty()) {
            log.warn("Nothing to delete by this id.");
            throw new UnexistanceException("Nothing to delete by this id");
        } else {
            return arrayList.get(0);
        }
    }

    @Override
    public ArrayList<Integer> delJ(String name) throws UnexistanceException, SQLException {
        String sql = "SELECT id FROM hotel WHERE hotelname = ?";
        ArrayList arrayList = udp.dataProcessor(cm, sql, name, udp::delter);
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = delJ");
        log.debug("INVOLVED_METHOD_TYPE = Main logic");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.debug("SQL: " +sql);
        log.info("Method returns ArrayList<Integer>.");
        if (arrayList.isEmpty()) {
            log.warn("Nothing to delete by this name.");
            throw new UnexistanceException("Nothing to delete by this name");
        } else {
            return arrayList;
        }
    }

    @Override
    public Integer patJ(RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException, SQLException {
        Integer rslt;
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = datJ");
        log.debug("INVOLVED_METHOD_TYPE = Main logic");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.info("Method returns Integer.");
        if (reqLin.getId() == null || reqLin.getId().equals("") || checkIdExistance(valueOf(reqLin.getId())) == null) {
            log.warn("There is no such entry.");
            log.info("Try another id.");
            throw new MisstargetException("There is no such entry");
        } else {
            if (reqLin.getHotelname() == null && reqLin.getAddress() == null) {
                log.warn("There is no such entry.");
                log.info("At least one field at least empty is required.");
                throw new EmptyBodyException("Sending request is empty");
            } else {
                RequestStructureFullLine rsfl = reqLin;
                if (reqLin.getHotelname() == null) {
                    rsfl.setHotelname(getHotelnameById(valueOf(reqLin.getId())));
                    udp.puter(cm, rsfl);
                }
                if (reqLin.getAddress() == null) {
                    rsfl.setAddress(getAddressById(valueOf(reqLin.getId())));
                    udp.puter(cm, rsfl);
                }
                if (reqLin.getHotelname() != null && reqLin.getAddress() != null) {
                    udp.puter(cm, reqLin);
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
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = getHotelMapByName");
        log.debug("INVOLVED_METHOD_TYPE = Auxiliary");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.debug("SQL: " +sql);
        return udp.dataProcessor(cm, sql, name);
    }

    @Override
    public Integer checkIdExistance(Integer cie) throws SQLException {
        String sql = "SELECT id, hotelname, address FROM hotel WHERE id = ?";
        Integer result;
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = checkIdExistance");
        log.debug("INVOLVED_METHOD_TYPE = Auxiliary");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.debug("SQL: " +sql);
        log.info("Method returns id or null.");
        if (udp.dataProcessor(cm, sql, cie).size() == 1) {
            Hotel receiving = (Hotel) udp.dataProcessor(cm, sql, cie).get(0);
            result = receiving.getId();
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public String getHotelnameById(Integer id) throws SQLException {
        String sql = "SELECT hotelname FROM hotel WHERE id = ?";
        String result = null;
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = getHotelnameById");
        log.debug("INVOLVED_METHOD_TYPE = Auxiliary");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.debug("SQL: " +sql);
        log.info("Method returns String.");
        if (cm.isConnectionSuccess()) {
            try (PreparedStatement ps = cm.connect().prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result = rs.getString("hotelname");
                    }
                    return result;
                }
            } catch (SQLException sqle) {
                log.warn("SQLException handled.");
                //sqle.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public String getAddressById(Integer id) throws SQLException {
        String sql = "SELECT address FROM hotel WHERE id = ?";
        String result = null;
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = getAddressById");
        log.debug("INVOLVED_METHOD_TYPE = Auxiliary");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.debug("SQL: " +sql);
        log.info("Method returns String.");
        if (cm.isConnectionSuccess()) {
            try (PreparedStatement ps = cm.connect().prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result = rs.getString("address");
                    }
                    return result;
                }
            } catch (SQLException sqle) {
                log.warn("SQLException handled");
                //sqle.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public ArrayList<Integer> getIdByName(String name) throws SQLException {
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = getIdByName");
        log.debug("INVOLVED_METHOD_TYPE = Auxiliary");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.info("Accept String");
        log.info("Method returns ArrayList<Integer> filled by ids.");
        return caster(getHotelMapByName(name));
    }

    @Override
    public ArrayList<Integer> caster(ArrayList<Hotel> cst) {
        ArrayList<Integer> rslt = new ArrayList<>();
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("INVOLVED_METHOD_NAME = caster");
        log.debug("INVOLVED_METHOD_TYPE = Auxiliary");
        log.debug("INVOLVED_METHOD_BELONGING = HotelServiceJDBC");
        log.info("Method transform ArrayList<Hotel> to ArrayList<Integer> filled by hotels ids.");
            for (Hotel x: cst) {
                rslt.add(x.getId());
            }
            return rslt;
    }

}
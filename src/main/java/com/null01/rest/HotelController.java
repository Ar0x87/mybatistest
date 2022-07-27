package com.null01.rest;

import com.null01.exceptions.*;
import com.null01.annotations.EnableResponseWrapper;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.RequestStructureFullLine;
import com.null01.services.HotelService;
import com.null01.wrappers.Wrapper;

import java.sql.SQLException;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class.
 * Accept web requests and transmits them to service.
 * Require lombok annotations.
 *
 * @Authors Vladimir, Evgeniy
 * @Version 1.0
 * @Since 09.06.2022
 */

@RestController
@RequiredArgsConstructor
@EnableResponseWrapper(wrapperClass = Wrapper.class)
public class HotelController {

    @Autowired
    private final HotelService hotelService;

    @GetMapping
    @RequestMapping("/getAll")
    public ArrayList<Hotel> getAll() throws SQLException {
        return hotelService.getAll();
    }

    @GetMapping
    @RequestMapping("/getByName")
    public ArrayList<Hotel> getByName(@RequestParam(value = "name") String name) throws SQLException {
        return hotelService.getByName(name);
    }

    @PostMapping(value = "/postJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer postJ(@RequestBody RequestStructure reqBod) throws AlreadyExistException, SQLException {
        return hotelService.postJ(reqBod);
    }

    @PutMapping(value = "/putJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer putJ(@RequestBody RequestStructureFullLine reqLin) throws UnexistanceException, SQLException {
        return hotelService.putJ(reqLin);
    }

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"id"})
    public Integer delJ(@RequestParam(value = "id")Integer id) throws UnexistanceException, SQLException {
        return hotelService.delJ(id);
    }

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"name"})
    public ArrayList<Integer> delJ(@RequestParam(value = "name")String name) throws UnexistanceException, SQLException {
        return hotelService.delJ(name);
    }

    @PatchMapping(value = "/patJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer patJ(@RequestBody RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException, SQLException {
        return hotelService.patJ(reqLin);
    }

}
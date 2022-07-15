package com.null01.rest;

import com.null01.Exceptions.AlreadyExistException;
import com.null01.Exceptions.EmptyBodyException;
import com.null01.Exceptions.MisstargetException;
import com.null01.Exceptions.UnexistanceException;
import com.null01.models.Hotel;
import com.null01.Requests.RequestStructure;
import com.null01.Requests.RequestStructureFullLine;
import com.null01.services.HotelService;
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
public class HotelController {


    @Autowired
    private final HotelService hotelService;
    //private static final Logger Logger = LoggerFactory.getLogger(HotelController.class);

    @GetMapping
    @RequestMapping("/getAll")
    public ArrayList<Hotel> getAll() {
        return hotelService.getAll();
    }

    @GetMapping
    @RequestMapping("/getByName")
    public ArrayList<Hotel> getByName(@RequestParam(value = "name") String name) {
        return hotelService.getByName(name);
    }

    @PostMapping(value = "/postJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer postJ(@RequestBody RequestStructure reqBod) throws AlreadyExistException {
        return hotelService.postJ(reqBod);
    }

    @PutMapping(value = "/putJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer putJ(@RequestBody RequestStructureFullLine reqLin) throws UnexistanceException {
        return hotelService.putJ(reqLin);
    }

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"id"})
    public Integer delJ(@RequestParam(value = "id")Integer id) throws UnexistanceException {
        return hotelService.delJ(id);
    }

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"name"})
    public ArrayList<Integer> delJ(@RequestParam(value = "name")String name) throws UnexistanceException {
        return hotelService.delJ(name);
    }

    @PatchMapping(value = "/patJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer patJ(@RequestBody RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException {
        return hotelService.patJ(reqLin);
    }

}
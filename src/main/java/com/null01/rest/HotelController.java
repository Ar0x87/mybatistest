package com.null01.rest;

import com.null01.Exeptions.AlreadyExistExeption;
import com.null01.Exeptions.EmptyBodyException;
import com.null01.Exeptions.MisstargetException;
import com.null01.models.Hotel;
import com.null01.models.RequestStructure;
import com.null01.models.RequestStructureFullLine;
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
    public Integer postJ(@RequestBody RequestStructure reqBod) {
        Integer post = null;
        try {
            post = hotelService.postJ(reqBod);
            return post;
        } catch (AlreadyExistExeption aee) {
            aee.printStackTrace();
        }
        return post;
    }

    @PutMapping(value = "/putJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer putJ(@RequestBody RequestStructureFullLine reqLin) {
        Integer put = null;
        try {
            put = hotelService.putJ(reqLin);
            return put;
        }
        catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return put;
    }

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"id"})
    public Integer delJ(@RequestParam(value = "id")Integer id) {
        Integer del = null;
        try {
            del = hotelService.delJ(id);
            return del;
        }
        catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return del;
    }

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"name"})
    public Integer delJ(@RequestParam(value = "name")String name) {
        Integer del = null;
        try {
            del = hotelService.delJ(name);
            return del;
        }
        catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        return del;
    }

    @PatchMapping(value = "/patJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer patJ(@RequestBody RequestStructureFullLine reqLin) {
        Integer pat = null;
        try {
            pat = hotelService.patJ(reqLin);
            return pat;
        }
        catch (EmptyBodyException ebe) {
            ebe.printStackTrace();
        }
        catch (MisstargetException | NullPointerException mtnpe) {
            mtnpe.getCause();
        }
        return pat;
    }

}
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        //Integer post = null;
        //try {
            Integer post = hotelService.postJ(reqBod);
            return post;
        /*} catch (AlreadyExistException aee) {
            aee.printStackTrace();
        }
        return post;*/
    }

    @PutMapping(value = "/putJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer putJ(@RequestBody RequestStructureFullLine reqLin) throws UnexistanceException {
        Integer put = null;
        //try {
            put = hotelService.putJ(reqLin);
            return put;
        /*}
        catch (UnexistanceException uee) {
            uee.printStackTrace();
        }
        return put;*/
    }

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"id"})
    public Integer delJ(@RequestParam(value = "id")Integer id) throws UnexistanceException {
        Integer del = null;
        //try {
            del = hotelService.delJ(id);
            return del;
        /*}
        catch (UnexistanceException uee) {
            uee.printStackTrace();
        }
        return del;*/
    }

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"name"})
    public ArrayList<Integer> delJ(@RequestParam(value = "name")String name) throws UnexistanceException {
        ArrayList<Integer> del = new ArrayList<>();
        //try {
            del = hotelService.delJ(name);
            return del;
        /*}
        catch (UnexistanceException uee) {
             handleUnexistanceException();
        }
        return del;*/
    }

    @PatchMapping(value = "/patJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer patJ(@RequestBody RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException {
        Integer pat = null;
        //try {
            pat = hotelService.patJ(reqLin);
            return pat;
        /*}
        catch (EmptyBodyException ebe) {
            ebe.printStackTrace();
        }
        catch (MisstargetException | NullPointerException mtnpe) {
            mtnpe.getCause();
        }
        return pat;*/
    }

    /*@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Entry do not exist!")
    @ExceptionHandler(UnexistanceException.class)
    public void handleUnexistanceException() {
        Logger.error("UnexistanceException handler executed.");
    }*/
}
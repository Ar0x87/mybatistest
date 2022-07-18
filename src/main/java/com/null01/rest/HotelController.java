package com.null01.rest;

import com.null01.exceptions.AlreadyExistException;
import com.null01.exceptions.EmptyBodyException;
import com.null01.exceptions.MisstargetException;
import com.null01.exceptions.UnexistanceException;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.RequestStructureFullLine;
import com.null01.responses.ApiAnswer;
import com.null01.services.HotelService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    /*@PostMapping(value = "/postJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer postJ(@RequestBody RequestStructure reqBod) throws AlreadyExistException {
        return hotelService.postJ(reqBod);
    }*/

    @PostMapping(value = "/postJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postJ(@RequestBody RequestStructure reqBod) throws AlreadyExistException {
        ArrayList<Integer> rslt = new ArrayList<>();
        rslt.add(hotelService.postJ(reqBod));
        ApiAnswer apiAnswer = new ApiAnswer(200, "OK", true, rslt);
        return new ResponseEntity<>(apiAnswer, HttpStatus.OK);
    }

    /*@PutMapping(value = "/putJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer putJ(@RequestBody RequestStructureFullLine reqLin) throws UnexistanceException {
        return hotelService.putJ(reqLin);
    }*/

    @PutMapping(value = "/putJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> putJ(@RequestBody RequestStructureFullLine reqLin) throws UnexistanceException {
        ArrayList<Integer> rslt = new ArrayList<>();
        rslt.add(hotelService.putJ(reqLin));
        ApiAnswer apiAnswer = new ApiAnswer(200, "OK", true, rslt);
        return new ResponseEntity<>(apiAnswer, HttpStatus.OK);
    }

    /*@DeleteMapping
    @RequestMapping(value = "/delJ", params = {"id"})
    public Integer delJ(@RequestParam(value = "id")Integer id) throws UnexistanceException {
        return hotelService.delJ(id);
    }*/

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"id"})
    public ResponseEntity<Object> delJ(@RequestParam(value = "id")Integer id) throws UnexistanceException {
        ArrayList<Integer> rslt = new ArrayList<>();
        rslt.add(hotelService.delJ(id));
        ApiAnswer apiAnswer = new ApiAnswer(200, "OK", true, rslt);
        return new ResponseEntity<>(apiAnswer, HttpStatus.OK);
    }

    /*@DeleteMapping
    @RequestMapping(value = "/delJ", params = {"name"})
    public ArrayList<Integer> delJ(@RequestParam(value = "name")String name) throws UnexistanceException {
        return hotelService.delJ(name);
    }*/

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"name"})
    public ResponseEntity<Object> delJ(@RequestParam(value = "name")String name) throws UnexistanceException {
        ApiAnswer apiAnswer = new ApiAnswer(200, "OK", true, hotelService.delJ(name));
        return new ResponseEntity<>(apiAnswer, HttpStatus.OK);
    }


    /*@PatchMapping(value = "/patJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer patJ(@RequestBody RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException {
        return hotelService.patJ(reqLin);
    }*/

    @PatchMapping(value = "/patJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> patJ(@RequestBody RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException {
        ArrayList<Integer> rslt = new ArrayList<>();
        rslt.add(hotelService.patJ(reqLin));
        ApiAnswer apiAnswer = new ApiAnswer(200, "OK", true, rslt);
        return new ResponseEntity<>(apiAnswer, HttpStatus.OK);
    }

}
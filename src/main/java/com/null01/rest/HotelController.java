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

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ValidateOnExecution;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
//@EnableResponseWrapper(wrapperClass = Wrapper.class)
public class HotelController {

    @Autowired
    private final HotelService hotelService;
    static final Logger log = LoggerFactory.getLogger(HotelController.class);

    @GetMapping
    @RequestMapping("/getAll")
    public ArrayList<Hotel> getAll() throws SQLException {
        log.debug("*-----------------------------------------------------------------------------------------*");
        log.debug("Request received and passed to the service.");
        log.debug("Involved method REST_TYPE = GET");
        log.debug("Involved method NAME = getAll");
        log.info("0 input parameters.");
        log.info("EXPECTED_NUMBER_OF_RESULTS = ?");
        log.info("EXPECTED_TYPE_OF_RESULT = Hotel.class");
        return hotelService.getAll();
    }

    @GetMapping
    @RequestMapping("/getByName")
    public ArrayList<Hotel> getByName(@RequestParam(value = "name") String name) throws SQLException {
        log.debug("*-----------------------------------------------------------------------------------------*");
        log.debug("Request received and passed to the service.");
        log.debug("Involved method REST_TYPE = GET");
        log.debug("Involved method NAME = getByName");
        log.info("1 input parameter: name = " +name);
        log.info("EXPECTED_NUMBER_OF_RESULTS = ?");
        log.info("EXPECTED_TYPE_OF_RESULT = Hotel.class");
        return hotelService.getByName(name);
    }

    //@PostMapping(value = "/postJ", consumes = MediaType.APPLICATION_JSON_VALUE)
   /* @PostMapping("/postJ")
    public Integer postJ(@Valid @RequestBody RequestStructure reqBod) throws AlreadyExistException, SQLException {
        log.debug("*-----------------------------------------------------------------------------------------*");
        log.debug("Request received and passed to the service.");
        log.debug("Involved method REST_TYPE = POST");
        log.debug("Involved method NAME = postJ");
        //log.info("2 input parameters: name = " +reqBod.getHotelname() +"; address = " +reqBod.getAddress());
        log.info("EXPECTED_NUMBER_OF_RESULTS = 1");
        log.info("EXPECTED_TYPE_OF_RESULT = Integer");
        return hotelService.postJ(reqBod);
    }*/

    @PutMapping(value = "/putJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer putJ(@Valid @RequestBody RequestStructureFullLine reqLin) throws UnexistanceException, SQLException {
        log.debug("*-----------------------------------------------------------------------------------------*");
        log.debug("Request received and passed to the service.");
        log.debug("Involved method REST_TYPE = PUT");
        log.debug("Involved method NAME = putJ");
        log.info("3 input parameters: id = " +reqLin.getId() +"; name = " +reqLin.getHotelname() +"; address = " +reqLin.getAddress());
        log.info("EXPECTED_NUMBER_OF_RESULTS = 1");
        log.info("EXPECTED_TYPE_OF_RESULT = Integer");
        return hotelService.putJ(reqLin);
    }

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"id"})
    public Integer delJ(@RequestParam(value = "id")Integer id) throws UnexistanceException, SQLException {
        log.debug("*-----------------------------------------------------------------------------------------*");
        log.debug("Request received and passed to the service.");
        log.debug("Involved method REST_TYPE = DELETE");
        log.debug("Involved method NAME = delJ");
        log.info("1 input parameter: id = " +id);
        log.info("EXPECTED_NUMBER_OF_RESULTS = 1");
        log.info("EXPECTED_TYPE_OF_RESULT = Integer");
        return hotelService.delJ(id);
    }

    @DeleteMapping
    @RequestMapping(value = "/delJ", params = {"name"})
    public ArrayList<Integer> delJ(@RequestParam(value = "name")String name) throws UnexistanceException, SQLException {
        log.debug("*-----------------------------------------------------------------------------------------*");
        log.debug("Request received and passed to the service.");
        log.debug("Involved method REST_TYPE = DELETE");
        log.debug("Involved method NAME = delJ");
        log.info("1 input parameter: name = " +name);
        log.info("EXPECTED_NUMBER_OF_RESULTS = ?");
        log.info("EXPECTED_TYPE_OF_RESULT = Integer");
        return hotelService.delJ(name);
    }

    @PatchMapping(value = "/patJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer patJ(@Valid @RequestBody RequestStructureFullLine reqLin) throws EmptyBodyException, MisstargetException, SQLException {
        log.info("*-----------------------------------------------------------------------------------------*");
        log.debug("Request received and passed to the service.");
        log.debug("Involved method REST_TYPE = PATCH");
        log.debug("Involved method NAME = patJ");
        log.info("2 or more input parameters: id = " +reqLin.getId() +"; name = " +reqLin.getHotelname() +"; (or/and) address = " +reqLin.getAddress());
        log.info("EXPECTED_NUMBER_OF_RESULTS = 1");
        log.info("EXPECTED_TYPE_OF_RESULT = Integer");
        return hotelService.patJ(reqLin);
    }

    @PostMapping("/valPo")
    public ResponseEntity<String> valPo(@RequestBody @Valid RequestStructure input) {
        return ResponseEntity.ok("Valid");
    }
}
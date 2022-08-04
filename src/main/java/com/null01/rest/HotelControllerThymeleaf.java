package com.null01.rest;

import com.null01.annotations.EnableResponseWrapper;
import com.null01.exceptions.AlreadyExistException;
import com.null01.exceptions.EmptyBodyException;
import com.null01.exceptions.MisstargetException;
import com.null01.exceptions.UnexistanceException;
import com.null01.models.Hotel;
import com.null01.requests.RequestStructure;
import com.null01.requests.StructureForPatch;
import com.null01.requests.StructureForPut;
import com.null01.services.HotelService;
import com.null01.wrappers.Wrapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
//@EnableResponseWrapper(wrapperClass = Wrapper.class)
public class HotelControllerThymeleaf {

    @Autowired
    private final HotelService hotelService;
    static final Logger log = LoggerFactory.getLogger(HotelController.class);

    @GetMapping("/getAll")
    public ModelAndView getAll() throws SQLException {
        ArrayList<Hotel> hotels = hotelService.getAll();
        Map<String, Object> params = new HashMap<>();
        params.put("hotels", hotels);
        return new ModelAndView("returnsHotels", params);
    }

    @GetMapping("/getByName")
    public ModelAndView getByName(@RequestParam(value = "name") String name) throws SQLException {
        ArrayList<Hotel> hotels = hotelService.getByName(name);
        Map<String, Object> params = new HashMap<>();
        params.put("hotels", hotels);
        return new ModelAndView("returnsHotels", params);
    }

    @PostMapping(value = "/postJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView postJ(@Valid @RequestBody RequestStructure reqBod) throws AlreadyExistException, SQLException {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(hotelService.postJ(reqBod));
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        return new ModelAndView("returnsIds", params);
    }

    @PutMapping(value = "/putJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView putJ(@Valid @RequestBody StructureForPut reqLin) throws UnexistanceException, SQLException {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(hotelService.putJ(reqLin));
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        return new ModelAndView("returnsIds", params);
    }

    @DeleteMapping(value = "/delJ", params = {"id"})
    public ModelAndView delJ(@RequestParam(value = "id")Integer id) throws UnexistanceException, SQLException {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(hotelService.delJ(id));
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        return new ModelAndView("returnsIds", params);
    }

    @DeleteMapping(value = "/delJ", params = {"name"})
    public ModelAndView delJ(@RequestParam(value = "name")String name) throws UnexistanceException, SQLException {
        ArrayList<Integer> ids = hotelService.delJ(name);
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        return new ModelAndView("returnsIds", params);
    }

    @PatchMapping(value = "/patJ", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView patJ(@Valid @RequestBody StructureForPatch reqLin) throws EmptyBodyException, MisstargetException, SQLException {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(hotelService.patJ(reqLin));
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        return new ModelAndView("returnsIds", params);
    }

}

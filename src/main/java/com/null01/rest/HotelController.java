package com.null01.rest;

import com.null01.services.HotelService;
import com.null01.models.Hotel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    @RequestMapping( "/getAll")
    final ArrayList<Hotel> getAll(){
         return hotelService.getAll();
    }

    @GetMapping
    @RequestMapping("/getByName")
    final ArrayList<Hotel> getByName(@RequestParam(value = "name")String name){
        return hotelService.getByName(name);
    }

    }


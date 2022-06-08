package com.null01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @RequestMapping( "/getAll")
    public ArrayList getAll(){
         return (ArrayList) new HotelServiceImpl().getAll();
        }

    @RequestMapping("/getByName")
    public ArrayList getByName(@RequestParam(value = "name", required = true)String name){
        return (ArrayList) new HotelServiceImpl().getByName(name);
    }

    }


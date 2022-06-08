package com.null01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @RequestMapping( "/getAll")
    public HotelService hotelService(){
         return (HotelService) new HotelServiceImpl().getAll();
        }

    @RequestMapping("/getByName")
    public HotelService hotelService(@RequestParam(value = "name", required = true)String name){
        return (HotelService) new HotelServiceImpl().getByName(name);
    }

    }


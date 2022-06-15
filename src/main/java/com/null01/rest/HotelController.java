package com.null01.rest;

import com.null01.models.Hotel;
import com.null01.services.HotelService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

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
    private HotelService hotelService;

    @GetMapping
    @RequestMapping("/getAll")
    final ArrayList<Hotel> getAll() {
        return hotelService.getAll();
    }

    @GetMapping
    @RequestMapping("/getByName")
    final ArrayList<Hotel> getByName(@RequestParam(value = "name")String name) {
        return hotelService.getByName(name);
    }

    @PostMapping(value = "/postJ", consumes = "application/json", produces = "application/json")
    final ArrayList<Hotel> postJ(@RequestBody Hotel newH) {
        return hotelService.postJ(newH);
    }
}
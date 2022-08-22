package com.null01.rest;

import com.null01.requests.RequestStructure;
import com.null01.requests.StructureForDel;
import com.null01.requests.StructureForPatch;
import com.null01.requests.StructureForPut;
import com.null01.services.HotelService;
import com.null01.wrappers.Errorer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;


@Controller
@RequiredArgsConstructor
public class HotelControllerThymeleaf {

    @Autowired
    private final HotelService hotelService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        Errorer errorer = new Errorer(400, "BAD_REQUEST", false, "Wrong address");
        model.addAttribute("errorer", errorer);
        return "index";
    }

    @RequestMapping(value = {"/hotels"}, method = RequestMethod.GET)
    public String getAll(Model model) throws SQLException {
        model.addAttribute("hotels", hotelService.getAll());
        return "hotels";
    }

    @RequestMapping(value = {"/findHotel"}, method = RequestMethod.GET)
    public String showFoundHotel(Model model) {
        String name = new String();
        model.addAttribute("name", name);
        return "findHotels";
    }

    @RequestMapping(value = "/findHotels", method = RequestMethod.GET)
    public String findByName(Model model, @ModelAttribute("name") String name) throws SQLException {
        model.addAttribute("foundResponse", hotelService.getByName(name));
        return "foundResponse";
    }

    @RequestMapping(value = {"/addHotel"}, method = RequestMethod.GET)
    public String showAddedHotel(Model model) {
        RequestStructure hotelForm = new RequestStructure();
        model.addAttribute("hotelForm", hotelForm);
        return "addHotel";
    }

    @RequestMapping(value = {"/addHotel"}, method = RequestMethod.POST)
    public String postHotel(Model model, @ModelAttribute("hotelForm") @Valid RequestStructure hotelForm, Errors errors) throws SQLException {
        if (errors.hasErrors()) {
            return "addHotel";
        }
        model.addAttribute("respondent", hotelService.postJ(hotelForm));
        return "respondentContent";
    }

    @RequestMapping(value = {"/putHotel"}, method = RequestMethod.GET)
    public String showPutedHotel(Model model) {
        StructureForPut hotelForm = new StructureForPut();
        model.addAttribute("hotelForm", hotelForm);
        return "putHotel";
    }

    @RequestMapping(value = {"/putHotel"}, method = RequestMethod.PUT)
    public String putHotel(Model model, @ModelAttribute("hotelForm") @Valid StructureForPut hotelForm) throws SQLException {
        model.addAttribute("respondent", hotelService.putJ(hotelForm));
        return "respondentContent";
    }

    @RequestMapping(value = {"/patHotel"}, method = RequestMethod.GET)
    public String showPatchedHotel(Model model) {
        StructureForPatch hotelForm = new StructureForPatch();
        model.addAttribute("hotelForm", hotelForm);
        return "patHotel";
    }

    @RequestMapping(value = {"/patHotel"}, method = RequestMethod.PATCH)
    public String patHotel(Model model, @ModelAttribute("hotelForm") @Valid StructureForPatch hotelForm) throws SQLException {
        model.addAttribute("respondent", hotelService.patJ(hotelForm));
        return "respondentContent";
    }

    @RequestMapping(value = {"/delHotel"}, method = RequestMethod.GET)
    public String showDeletingHotel(Model model) {
        StructureForDel delForm = new StructureForDel();
        model.addAttribute("delForm", delForm);
        return "delHotel";
    }

    @RequestMapping(value = {"/delHotel"}, method = RequestMethod.DELETE)
    public String delHotel(Model model, @ModelAttribute("delForm") @Valid StructureForDel delForm) throws SQLException {
        ArrayList<Integer> respondent = new ArrayList<>();
        if (delForm.getId() != null && (delForm.getHotelname() == null || delForm.getHotelname().equals(""))) {
            respondent.add(hotelService.delJ(Integer.valueOf(delForm.getId())));
        } else if (delForm.getHotelname() != null && !delForm.getHotelname().equals("")) {
            respondent = hotelService.delJar(delForm.getHotelname());
        }
        model.addAttribute("respondent", respondent);
        return "respondentContent";
    }

}

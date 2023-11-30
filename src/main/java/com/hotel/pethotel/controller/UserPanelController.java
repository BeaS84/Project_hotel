package com.hotel.pethotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/userpanel")
public class UserPanelController {

    @GetMapping("/userReservation")
    public String showUserReservation() {
        return "userReservation";
    }

    @GetMapping("/userAnimals")
    public String showUserAnimals() {
        return "userAnimals";
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
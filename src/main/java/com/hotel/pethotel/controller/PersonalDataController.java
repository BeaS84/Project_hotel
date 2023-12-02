package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.UserModel;
import com.hotel.pethotel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/clientpanel")
public class PersonalDataController {
    private final UserService userService;

    public PersonalDataController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/personalData")
//    public String showPersonalData() {
//
//        return "personalData";
//    }
    @GetMapping("/personalData")
    public String showPersonalData(Model model, Principal principal) {
        String userEmail = principal.getName();
        UserModel user = userService.getUserByEmail(userEmail);

        if (user != null) {
            model.addAttribute("user", user);
        }

        return "personalData";
    }
}

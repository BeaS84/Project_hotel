package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.ConcreteUserModel;
import com.hotel.pethotel.model.UserModel;
import com.hotel.pethotel.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/clientpanel")
public class PersonalController {

    private final UserService userService;
    @GetMapping("/personalData")
    public String showPersonalPage(Model model, Principal principal) {
        String userEmail = principal.getName();
        UserModel user = userService.getUserByEmail(userEmail);

        if (user != null) {
            model.addAttribute("user", user);
        }

        return "Client/personal";
    }

    public PersonalController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/personalData/editPersonal")
    public String showEditPersonalForm(Model model, Principal principal) {
        String userEmail = principal.getName();
        UserModel user = userService.getUserByEmail(userEmail);

        if (user != null) {
            model.addAttribute("user", user);
            return "Client/editPersonal";
        } else {
            System.out.println("Nie można znaleźć użytkownika o adresie e-mail: " + userEmail);
            return "redirect:/clientpanel/personalData";
        }
    }
    @PostMapping("/personalData/editPersonal")
    public String saveEditPersonalForm(@ModelAttribute("user") ConcreteUserModel editedUser, Principal principal) {
        String userEmail = principal.getName();
        UserModel user = userService.getUserByEmail(userEmail);

        if (user != null) {
            user.setSurname(editedUser.getSurname());
            user.setPhone(editedUser.getPhone());

            userService.saveEditPersonalData(user);
        }

        return "redirect:/clientpanel/personalData";
    }

}

package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.UserModel;
import com.hotel.pethotel.service.AnimalService;
import com.hotel.pethotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AnimalService animalService;
    private final UserService userService;

    @GetMapping("/allClients")
    public String listClients(Model model) {
        List<UserModel> clientList = userService.getAllClients();
        model.addAttribute("clients",clientList);
        return "clients";
    }
    @GetMapping("/allAnimals")
    public String listAnimals(Model model){
        List<AnimalModel>animalList = animalService.getAnimalList(model);
        model.addAttribute("animals", animalList);
        return "animals";
    }

    @GetMapping("/allReservations")
    public String getAllReservation() {
        return "reservations";
    }

//    @GetMapping("/allClients")
//    public String getAllClients() {
//        return "clients";
//    }

    @GetMapping("/allRooms")
    public String getAllRooms() {
        return "rooms";
    }
}

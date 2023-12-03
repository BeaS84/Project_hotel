package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.model.UserModel;
import com.hotel.pethotel.service.AnimalService;
import com.hotel.pethotel.service.ClientService;
import com.hotel.pethotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clientpanel")
public class ClientPanelController {
    private final AnimalService animalService;
    private final UserService userService;
    private final ClientService clientService;


    @GetMapping("/clientReservations")
    public String showClientReservation() {
        return "clientReservations";
    }

    @GetMapping("/clientAnimals")
    public String listAnimals(Model model) {
        List<AnimalModel> animalList = animalService.getAnimalList();
        model.addAttribute("clientAnimals", animalList);
        return "clientAnimals";
    }

    @GetMapping("/clientAnimals/addAnimal")
    public String getAddClientAnimal(Model model) {
        model.addAttribute("newAnimal", new AnimalModel());
        return "addAnimal";
    }

    @PostMapping("/clientAnimals/addAnimal")
    public RedirectView postAddAnimal(@ModelAttribute("newAnimal") AnimalModel animal) {
        System.out.println("Received animal: " + animal);
        animalService.addAnimal(animal);
        return new RedirectView("/clientpanel/clientAnimals");
    }


    //    @PostMapping ("/clientAnimals/addAnimal")
//    public RedirectView postAddAnimal(AnimalModel animal){
//        animalService.addAnimal(animal);
//        return new RedirectView("/clientpanel/clientAnimals");
//    }
    @GetMapping("/clientAnimals/editanimal/{id}")
    public String getEditAnimal(@PathVariable("id") String id, Model model) {
        Long animalId = Long.parseLong(id);
        AnimalModel animalModel = animalService.getAnimalById(animalId);
        model.addAttribute("editedAnimal", animalModel);
        return "editAnimal";
    }



    @PostMapping("/clientAnimals/editanimal")
    public RedirectView postEditAnimal(@ModelAttribute("editedAnimal") AnimalModel editedAnimal) {
        animalService.saveEditAnimal(editedAnimal);
        return new RedirectView("/clientpanel/clientAnimals");
    }


//    @GetMapping("/personalData")
//    public String showPersonalData(Model model, Principal principal) {
//        String userEmail = principal.getName();
//        UserModel user = userService.getUserByEmail(userEmail);
//
//        if (user != null) {
//            model.addAttribute("user", user);
//        }
//
//        return "personalData";
//    }

    @PostMapping("/clientAnimals")
    public String showClientAnimals() {
        return "clientAnimals";
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

   }
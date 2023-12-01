package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.service.AnimalService;
import com.hotel.pethotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clientpanel")
public class ClientPanelController {
    private final AnimalService animalService;
    private final UserService userService;


    @GetMapping("/clientReservations")
    public String showClientReservation() {
        return "clientReservations";
    }

    @GetMapping("/clientAnimals")
    public String listAnimals(Model model){
        List<AnimalModel> animalList = animalService.getAnimalList(model);
        model.addAttribute("clientAnimals", animalList);
        return "clientAnimals";
    }

    @GetMapping("/clientAnimals/addAnimal")
    public String getAddClientAnimal(Model model) {
        model.addAttribute("newAnimal", new AnimalModel());
        return "addAnimal";}

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





    @PostMapping("/clientAnimals")
    public String showClientAnimals() {
        return "clientAnimals";
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
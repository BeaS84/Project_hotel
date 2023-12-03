package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.service.AnimalService;
import com.hotel.pethotel.service.ClientService;
import com.hotel.pethotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clientpanel")
public class ClientPanelController {
    private final AnimalService animalService;
    private final ClientService clientService;
    private final UserService userService;

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/clientReservations")
    public String showClientReservation() {
        return "clientReservations";
    }


/////ANIMALS FLOW////////
    @GetMapping("/clientAnimals/client/{email}")
    public String getAnimalsList(Model model, @PathVariable String email) {
        List<AnimalModel> animals = clientService.getAnimalsByClientEmail(email);
        //System.out.println(clientService.getClientByEmail(email));//com.hotel.pethotel.model.ClientModel@6c562f7e
        model.addAttribute("animals", animals);
        //dodałam
        model.addAttribute("clientEmail", email);
//        System.out.println(email);//user@user.com
        return "Animals/clientAnimals";
    }


    @GetMapping("/clientAnimals/client/{email}/addAnimal")
    public String getAddClientAnimal(@PathVariable String email,Model model) {
        model.addAttribute("newAnimal", new AnimalModel());
        model.addAttribute("clientEmail", email);
        System.out.println(email);//user@user.com
        return "Animals/addAnimal";
    }

    @PostMapping("/clientAnimals/client/{email}/addAnimal")
    public RedirectView postAddClientAnimal(
            @PathVariable String email, @ModelAttribute("newAnimal") AnimalModel newAnimal, Model model) {
        model.addAttribute("clientEmail", email);
        //ustawiamy klienta dla nowego zwierzaka - bez tego nie doda sie zwierzak
        newAnimal.setClient(clientService.getClientByEmail(email));
        animalService.saveAnimal(newAnimal);
        return new RedirectView("/clientpanel/clientAnimals/client/" + email);
    }

    @GetMapping("/clientAnimals/client/{email}/editAnimal/{id}")
    public String getEditAnimal(@PathVariable String email,@PathVariable("id") Long id, Model model) {
        AnimalModel animalModel = animalService.getAnimalById(id);
        model.addAttribute("editedAnimal", animalModel);
        model.addAttribute("clientEmail", email);
        return "Animals/editAnimal";
    }

    @PostMapping("/clientAnimals/client/{email}/editAnimal/{id}")
    public RedirectView postEditAnimal(@PathVariable String email,@PathVariable("id") Long id,@ModelAttribute("editedAnimal") AnimalModel editedAnimal) {
        // Ustawiamy klienta dla edytowanego zwierzaka
        editedAnimal.setClient(clientService.getClientByEmail(email));
        // Ustawiamy ID zwierzaka
        editedAnimal.setId(id);
        animalService.saveEditAnimal(editedAnimal);
        return new RedirectView("/clientpanel/clientAnimals/client/" + email);
    }

}
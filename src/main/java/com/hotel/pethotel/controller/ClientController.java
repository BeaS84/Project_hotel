package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.repository.ClientRepository;
import com.hotel.pethotel.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    //dubel w adminController
    @GetMapping ("/client/{id}")
    public String getAnimals(@PathVariable("id") Long id, Model model) {
        ClientModel clientModel = clientService.getClientById(id);
        List<AnimalModel> animals=clientModel.getAnimals();
        model.addAttribute("animal", animals);
        return "animalsForClient";
    }


}

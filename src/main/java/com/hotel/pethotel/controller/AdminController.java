package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.model.ReservationModel;
import com.hotel.pethotel.model.UserModel;
import com.hotel.pethotel.service.AnimalService;
import com.hotel.pethotel.service.ClientService;
import com.hotel.pethotel.service.ReservationService;
import com.hotel.pethotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
@RequiredArgsConstructor
@Controller
@RequestMapping("/adminpanel")
public class AdminController {

    private final AnimalService animalService;
    private final ClientService clientService;
    private final ReservationService reservationService;
    @GetMapping("/allClients")
    public String listClients(Model model) {
        List<ClientModel> clientList = clientService.getAllClients();
        model.addAttribute("clients",clientList);
        return "clients";
    }
    @GetMapping("/allAnimals")//animals
    // dodaj email właściciela owner=findById() później owner.email
    public String listAnimals(Model model){
        List<AnimalModel>animalList = animalService.getAnimalList();
        model.addAttribute("animals", animalList);
        return "animals";
    }
    @GetMapping("/allReservations")
    public String listReservations(Model model) {
        List<ReservationModel> reservationList = reservationService.getAllReservationList();
        model.addAttribute("reservations", reservationList);



        return "reservations";
    }

//    @GetMapping("/allReservationsPage")
//    public String getAllReservation() {
//        return "reservations";
//    }


//    @GetMapping("/allRooms")
//    public String getAllRooms() {
//        return "rooms";
//    }


    @GetMapping ("/client/{id}")//clients/2/animals
    public String getAnimals(@PathVariable("id") Long id, Model model) {
        ClientModel clientModel = clientService.getClientById(id);
        List<AnimalModel> animals = clientModel.getAnimals();
        model.addAttribute("animals", animals);
        model.addAttribute("client", clientModel);
        return "animalsForClient";
    }


}

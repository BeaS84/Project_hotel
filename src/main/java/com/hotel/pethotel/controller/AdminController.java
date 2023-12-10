package com.hotel.pethotel.controller;

import com.hotel.pethotel.Reservation.ReservationModel;
import com.hotel.pethotel.Reservation.ReservationService;
import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.model.UserModel;
import com.hotel.pethotel.service.AnimalService;
import com.hotel.pethotel.service.ClientService;
import com.hotel.pethotel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("clients", clientList);
        return "clients";
    }

    @GetMapping("/allAnimals")//animals
    // dodaj email właściciela owner=findById() później owner.email
    public String listAnimals(Model model) {
        List<AnimalModel> animalList = animalService.getAnimalList();
        model.addAttribute("animals", animalList);
        return "animals";
    }

    @GetMapping("/allReservations")
    public String listReservations(Model model) {
        List<ReservationModel> reservations = reservationService
                .getAllReservationList();
        model.addAttribute("reservations", reservations);
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


    @GetMapping("/client/{id}")//clients/2/animals
    public String getAnimals(@PathVariable("id") Long id, Model model) {
        ClientModel clientModel = clientService.getClientById(id);
        List<AnimalModel> animals = clientModel.getAnimals();
        model.addAttribute("animals", animals);
        model.addAttribute("client", clientModel);
        return "animalsForClient";
    }

    @GetMapping("/client/{id}/reservations")
    public String getReservations(@PathVariable("id") Long id, Model model) {
        ClientModel clientModel = clientService.getClientById(id);
        List<ReservationModel> reservations = clientModel.getReservations();
        model.addAttribute("reservations", reservations);
        model.addAttribute("client", clientModel);
        return "Reservations/adminReservationForClient";
    }


    @GetMapping("/allReservations/filteredreservations")
    public String listFilteredReservations(@RequestParam(name = "status", required = false) String status, Model model) {
        System.out.println("Status: " + status);  // Dodaj tę linię do debugowania

        List<ReservationModel> reservationList;

        if (status != null && !status.isEmpty()) {
            reservationList = reservationService.getReservationsByStatus(status);
        } else {
            reservationList = reservationService.getAllReservationList();
        }

        model.addAttribute("filteredReservations", reservationList);
        return "filteredreservations";
    }


    // deleteReservationConfirm/
    @PostMapping("/deleteReservation/{id}")
    //@PutMapping służy do update, update całego zasobu, update całego obiektu,
    // przekazuje id
    //@PatchMapping, partial update, rzadkie, trudniejsze, update wybranych pól,
    // przekazuje id + RequestBody
    //Różnica międze @PostMapping i @DeleteMapping, tutaj/w RestController
    public RedirectView deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return new RedirectView("/adminpanel/allReservations");
    }

}

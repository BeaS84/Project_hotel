package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.ReservationModel;
import com.hotel.pethotel.repository.ReservationRepository;
import com.hotel.pethotel.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/adminpanel")
public class ReservationController {

    private final ReservationService reservationService;

//
//
//    @GetMapping("/reservations")
//    public String showReservations(Model model) {
//        List<ReservationModel> reservations = reservationService.getAllReservations();
//        model.addAttribute("reservations", reservations);
//        return "reservations";
//    }
}

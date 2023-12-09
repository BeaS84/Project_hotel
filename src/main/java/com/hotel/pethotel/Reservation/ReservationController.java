package com.hotel.pethotel.Reservation;

import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.Rooms.RoomService;
import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.service.AnimalService;
import com.hotel.pethotel.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clientpanel")
public class ReservationController {

    private final ReservationService reservationService;
    private final ClientService clientService;
    private final AnimalService animalService;
    private final RoomService roomService;


    @GetMapping("/create")
    public String getAaddReservation(@RequestParam Long animalId,
                                     @RequestParam Long roomId,
                                     @RequestParam LocalDate reservationStartDate,
                                     @RequestParam LocalDate reservationEndDate,
                                     Model model) {
        RoomModel room = roomService.getRoomById(roomId);
        BigDecimal totalPrice = reservationService.calculateReservationPrice(reservationStartDate,reservationEndDate,room.getCostPerNight());
        long durationInDays = ChronoUnit.DAYS.between(reservationStartDate,reservationEndDate);

        AnimalModel animal = animalService.getAnimalById(animalId);

        model.addAttribute("animal", animal);
        model.addAttribute("room", room);
        model.addAttribute("reservationStartDate", reservationStartDate);
        model.addAttribute("reservationEndDate", reservationEndDate);
        //nie wiem jak przekazac te dane ?
        model.addAttribute("duration", durationInDays);
        model.addAttribute("totalPrice", totalPrice);
        return "Reservations/reservationForm";
    }

    @PostMapping("/create")
    public RedirectView createReservation(@ModelAttribute ReservationRequest request, RedirectAttributes attributes) {
        try {
            // Pobierz klienta, zwierzę i pokój na podstawie przekazanych danych
            String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            ClientModel client = clientService.getClientByEmail(email);
            AnimalModel animal = animalService.getAnimalById(request.getAnimalId());
            RoomModel room = roomService.getRoomById(request.getRoomId());

            // Sprawdź czy obiekty są niepuste
            if (client == null || animal == null || room == null) {
                attributes.addFlashAttribute("error", "Nieprawidłowe dane klienta, zwierzęcia lub pokoju.");
                return new RedirectView("/errorPage");
            }

            // Utwórz rezerwację
            ReservationModel reservation = reservationService.createReservation(client, animal, room, request.getStartDate(), request.getEndDate());

            // Dodaj wiadomość o sukcesie
            attributes.addFlashAttribute("message", "Rezerwacja utworzona pomyślnie. ID rezerwacji: " + reservation.getId());

            // Przekieruj na "/clientpanel/clientReservations"
            return new RedirectView("/clientpanel/clientReservations");

        } catch (Exception e) {
            handleUnexpectedException(e, attributes);
            System.out.println("jakis blad");

            // W przypadku błędu przekieruj na "/errorPage"
            return new RedirectView("/clientpanel/errorPage");
        }
    }

//    private void handleReservationCreationException(Exception e, RedirectAttributes attributes) {
//        attributes.addFlashAttribute("error", "Błąd podczas tworzenia rezerwacji: " + e.getMessage());
//        // Logowanie błędu
//    }

    private void handleUnexpectedException(Exception e, RedirectAttributes attributes) {
        attributes.addFlashAttribute("error", "Nieoczekiwany błąd: " + e.getMessage());
    }
}


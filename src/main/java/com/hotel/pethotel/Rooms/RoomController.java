package com.hotel.pethotel.Rooms;

import com.hotel.pethotel.Exceptions.RoomValidationException;
import com.hotel.pethotel.Reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
//podajemy po to aby w sciezce nie dublowac adminpanel
@RequestMapping("/adminpanel")
public class RoomController {
    //tutaj musimy wstrzyknac service
    private final RoomService roomService;


    @GetMapping("/allRooms")
    public String getRoomList(Model model){
        List<RoomModel> rooms = roomService.getRoomList();
        rooms.forEach(room -> {

            if (room.getId()!= null) {
            room.setHasFutureReservation(roomService.isRoomIsReservedNowOrInFuture(room.getId()));
        }});

        model.addAttribute("rooms", rooms);
        return "Rooms/allRooms";
    }
    @GetMapping("/addRoom")
    public String getAddRoom(Model model) {
        model.addAttribute("newRoom", new RoomModel());
        return "Rooms/addRoom";}

    @PostMapping ("/addRoom")
        public RedirectView postAddRoom(@ModelAttribute RoomModel room, RedirectAttributes redirectAttributes) {
            try {
                roomService.addRoom(room);
                redirectAttributes.addFlashAttribute("message", "Room added successfully");
                return new RedirectView("/adminpanel/allRooms");
            } catch (RoomValidationException e) {
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return new RedirectView("/adminpanel/allRooms/error");
            } catch (RuntimeException e) {
                log.error("Error during room creation", e);
                redirectAttributes.addFlashAttribute("error", e.getMessage());
                return new RedirectView("/adminpanel/allRooms/error");
            }
    }

    @GetMapping("/editRoomNotAllowed")
    public String showEditRoomNotAllowedPage() {
        return "Rooms/editRoomNotAllowed";
    }

    @GetMapping("/allRooms/error")
        public String showErrorPage(@ModelAttribute("error") String error, Model model) {
            if (error != null && !error.isEmpty()) {
                model.addAttribute("errorMessage", error);
            } else {
                model.addAttribute("errorMessage", "Unknown error occurred");
            }
            return "Rooms/errorCreateEditRoom";
        }


    @GetMapping("/editRoom/{id}")
    public String getEditRoom(@PathVariable("id") Long id, Model model) {
        try {
            if (roomService.isRoomIsReservedNowOrInFuture(id)) {
                return "Rooms/editRoomNotAllowed";
            }
            RoomModel roomModel = roomService.getRoomById(id);
            model.addAttribute("editRoom", roomModel);
            return "Rooms/editRoom";
        } catch (RuntimeException e) {
            log.error("Error getting room for editing", e);
            return "redirect:/adminpanel/allRooms";
        }
    }

    @PostMapping("/editRoom/{id}")
    public String postEditRoom(@ModelAttribute RoomModel editRoom, RedirectAttributes redirectAttributes) {
        try {
            roomService.saveEditRoom(editRoom);
            redirectAttributes.addFlashAttribute("message", "Edycja zakończona pomyślnie");
            return "redirect:/adminpanel/allRooms";
        } catch (RuntimeException e) {
            log.error("Error editing room", e);
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/adminpanel/allRooms/error";
        }
    }
}

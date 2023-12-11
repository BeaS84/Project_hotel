package com.hotel.pethotel.Rooms;

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
    public RedirectView postAddRoom(RoomModel room){
        roomService.addRoom(room);
        return new RedirectView("/adminpanel/allRooms");
    }
    @GetMapping("/editRoomNotAllowed")
    public String showEditRoomNotAllowedPage() {
        return "Rooms/editRoomNotAllowed";
    }

    // @PathVariable("id") jest używane do określenia, którą zmienną ścieżkową przypisać do parametru "id". W tym przypadku jest używane konkretne nazwanie "id", które wskazuje, że wartość zmiennej ścieżkowej o nazwie "id" ma zostać przypisana do parametru "id" metody.
    //mozna tez tak (@PathVariable Long id, Model model_ --> @PathVariable jest używane bezpośrednio, bez określania nazwy zmiennej. W tym przypadku Spring automatycznie przypisuje wartość zmiennej ścieżkowej do parametru metody na podstawie ich typów i pozycji. Wartość zmiennej ścieżkowej jest przypisywana do parametru "id" ze względu na identyfikator "id" w ścieżce.
    @GetMapping("/editRoom/{id}")
    public String getEditRoom(@PathVariable("id") Long id, Model model) {
        if (roomService.isRoomIsReservedNowOrInFuture(id)) {
            return "Rooms/editRoomNotAllowed";
        }
        RoomModel roomModel = roomService.getRoomById(id);
        model.addAttribute("editRoom", roomModel);
        return "Rooms/editRoom";
    }

    @PostMapping("/editRoom/{id}")
    public String postEditRoom(@ModelAttribute RoomModel editRoom, RedirectAttributes redirectAttributes) {
        roomService.saveEditRoom(editRoom);
        redirectAttributes.addFlashAttribute("message", "Edycja zakończona pomyślnie");
        return "redirect:/adminpanel/allRooms";
    }
}

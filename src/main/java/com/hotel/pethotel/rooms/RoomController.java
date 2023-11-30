package com.hotel.pethotel.rooms;

import com.hotel.pethotel.model.AnimalSize;
import com.hotel.pethotel.model.Standard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomController {
    //tutaj musimy wstrzyknac service
    private final RoomService roomService;


    @GetMapping("/adminpanel/rooms")
    public String getRoomList(Model model){
        List<RoomModel> rooms = roomService.getRoomList();
        model.addAttribute("rooms", rooms);
        return "roomList";
    }
    @GetMapping("/adminpanel/addroom")
    public String getAddRoom(Model model) {
        model.addAttribute("newRoom", new RoomModel());
        return "addRoomForm";}
    @PostMapping ("/adminpanel/addroom")
    public RedirectView postAddRoom(RoomModel room){
        roomService.addRoom(room);
        return new RedirectView("/adminpanel/rooms");
    }

   // @PathVariable("id") jest używane do określenia, którą zmienną ścieżkową przypisać do parametru "id". W tym przypadku jest używane konkretne nazwanie "id", które wskazuje, że wartość zmiennej ścieżkowej o nazwie "id" ma zostać przypisana do parametru "id" metody.
    //mozna tez tak (@PathVariable Long id, Model model_ --> @PathVariable jest używane bezpośrednio, bez określania nazwy zmiennej. W tym przypadku Spring automatycznie przypisuje wartość zmiennej ścieżkowej do parametru metody na podstawie ich typów i pozycji. Wartość zmiennej ścieżkowej jest przypisywana do parametru "id" ze względu na identyfikator "id" w ścieżce.
    @GetMapping("/adminpanel/editroom/{id}")
    public String getEditRoom(@PathVariable("id") Long id, Model model) {
        RoomModel roomModel = roomService.getRoomById(id);
        model.addAttribute("editRoom", roomModel);
        return "editRoom";
    }
@PostMapping("/adminpanel/editroom/{id}")
    public RedirectView postEditRoom(@PathVariable Long id, RoomModel editRoom){
        roomService.saveEditRoom(editRoom);
    return new RedirectView("/adminpanel/rooms");
}


}

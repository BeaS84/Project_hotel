package com.hotel.pethotel.searcher;

import com.hotel.pethotel.dto.AnimalDto;
import com.hotel.pethotel.dto.RoomDto;
import com.hotel.pethotel.mapper.AnimalMapper;
import com.hotel.pethotel.mapper.RoomMapper;
import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.RoomModel;
import com.hotel.pethotel.model.Standard;
import com.hotel.pethotel.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clientpanel")
public class RoomSearchController {
    private final ClientService clientService;
    private final RoomSearchService roomSearchService;

    @GetMapping ("/searcher")
    public String listAnimalsToChoose(Model model) {
        String email = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        List<AnimalModel> animalList = clientService.getAnimalsByClientEmail(email);
        // Convert AnimalModel to AnimalDto using AnimalMapper
        List<AnimalDto> animalDtoList = animalList.stream()//TWORZE STREAM Z LISTY ANIMALlIST
                .map(AnimalMapper::toAnimalDto)//TUTAJ ITERUJE I PRZEKSZTALCAM OBIEKT TYPU ANIMALMODEL NA ANIMALDTO
                .collect(Collectors.toList());//ZBIERAM WYNIK PRZETWOREZENIA NA ANIMALDTO I ZBIERAMY ELEMENTY STRUMIENIA I ZAPISUJE JE DO LISTY

        model.addAttribute("clientAnimals", animalDtoList);
        model.addAttribute("availableStandards", Standard.values());
        model.addAttribute("searchQuery", new RoomSearchQueryDto());
        return "Searcher/Searcher";

    }

    @PostMapping("/search")
    public String showSearcherForm(@ModelAttribute RoomSearchQueryDto searchQuery, Model model) {
        List<RoomModel> rooms = roomSearchService.getAvailableRooms(searchQuery);
//COnvert RoomModel to RoomDto using RoomMapper
        List<RoomDto> roomDtoList = rooms.stream()
                        .map(RoomMapper::toRoomDto)
                                .collect(Collectors.toList());
        model.addAttribute("rooms", roomDtoList);
        return "Searcher/SearchResults";
    }

}

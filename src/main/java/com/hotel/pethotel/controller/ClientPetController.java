package com.hotel.pethotel.controller;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.service.ClientPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clientpanel")
public class ClientPetController {


    private final ClientPetService clientPetService;

    @GetMapping("/allPets/{email}")
    public String getPetsList(Model model, @PathVariable String email) {//Parametr Model model służy do
        // przekazywania danych do widoku, a @PathVariable Long clientId oznacza, że wartość parametru będzie
        // pobierana z części ścieżki URL o nazwie "clientId".
        List<AnimalModel> animals = clientPetService.getAnimalsByClientEmail(email);//wywołujemy metode z serwisu i
        // przekazujemy jej wartosc id klienta, jako argument. I to ma nam zwrocic liste zweirzat tego danego usera
        model.addAttribute("animals", animals);//dodaje atrybut animals do obiektu MOdel - atrybut bedzie widoczny w
        // w widoku i zawierać będzie listę zwierząt przypisanych do użytkownika
        return "clientPets"; //zwracam widok
    }
}


package com.hotel.pethotel.service;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.repository.AnimalRepository;
import com.hotel.pethotel.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientPetService {
    private final AnimalRepository animalRepository;
    private final ClientRepository clientRepository;

    public List<AnimalModel> getAnimalsByClientEmail(String email){
        ClientModel clientModel = clientRepository.findByEmail(email);
        return clientModel.getAnimals();
    }


}


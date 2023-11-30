package com.hotel.pethotel.service;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.model.UserModel;
import com.hotel.pethotel.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<AnimalModel> getAnimalListForClient(ClientModel clientModel) {
       // return clientRepository.getAnimals();
        return List.of();
    }
}

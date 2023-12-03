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

    public List<ClientModel> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientModel getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public ClientModel getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public List<AnimalModel> getAnimals(Long id){
        ClientModel clientModel=clientRepository.findById(id).orElse(null);
        assert clientModel != null;
        return clientModel.getAnimals();
    }

    public List<AnimalModel> getAnimalsByClientEmail(String email){
        ClientModel clientModel = clientRepository.findByEmail(email);
        return clientModel.getAnimals();
    }

}

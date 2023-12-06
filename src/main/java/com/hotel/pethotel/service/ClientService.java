package com.hotel.pethotel.service;

import com.hotel.pethotel.config.SecurityConfig;
import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.model.RoleModel;
import com.hotel.pethotel.model.UserModel;
import com.hotel.pethotel.repository.ClientRepository;
import com.hotel.pethotel.repository.RoleModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final RoleModelRepository roleRepository;


    public List<ClientModel> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientModel getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public ClientModel getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public void addClient(ClientModel client){//throws EmailExistsException{
        {
//         {
//            if (emailExist(accountDto.getEmail())) {
//                throw new EmailExistsException(
//                        "There is an account with that email adress:" + accountDto.getEmail());
//            }
            //   ClientModel newClient = new ClientModel();
            //  newClient.setEmail(client.getEmail());

            //   SecurityConfig.configure(AuthenticationManagerBuilder, client);

            client.setPassword(SecurityConfig.passwordEncoder()
                    .encode(client.getPassword()));

//        SecurityContextHolder
//                .getContext().getAuthentication().setRole
////                .getAuthorities().toString();

            // user.setEmail(accountDto.getEmail())
            RoleModel userRole = roleRepository.findByName("ROLE_USER").orElseThrow();
            client.getRoles().add(userRole);
            //  clientRepository.save(newClient);
        }
        clientRepository.save(client);
    }
//to jest zrobione w controllerze
//    public List<AnimalModel> getAnimals(Long id){
//        ClientModel clientModel=clientRepository.findById(id).orElse(null);
//        assert clientModel != null;
//        return clientModel.getAnimals();
//    }

    public List<AnimalModel> getAnimalsByClientEmail(String email){
        ClientModel clientModel = clientRepository.findByEmail(email);
        return clientModel.getAnimals();
    }

}

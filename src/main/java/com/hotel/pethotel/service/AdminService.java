package com.hotel.pethotel.service;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.UserModel;
import com.hotel.pethotel.repository.AnimalRepository;
import com.hotel.pethotel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

}

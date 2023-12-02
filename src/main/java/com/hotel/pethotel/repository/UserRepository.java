package com.hotel.pethotel.repository;

import java.util.Optional;

import com.hotel.pethotel.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<UserModel, Long> {
//    Optional<UserModel> findByEmail(String email);
Optional<UserModel> findByEmail(String userEmail);

}
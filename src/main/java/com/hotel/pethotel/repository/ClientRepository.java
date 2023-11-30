package com.hotel.pethotel.repository;

import com.hotel.pethotel.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}

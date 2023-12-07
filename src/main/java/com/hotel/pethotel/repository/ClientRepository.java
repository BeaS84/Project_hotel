package com.hotel.pethotel.repository;

import com.hotel.pethotel.model.ClientModel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
    @EntityGraph(attributePaths = {"animals"})
    ClientModel findByEmail(String email);
}

package com.hotel.pethotel.repository;

import com.hotel.pethotel.model.AnimalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<AnimalModel, Long> {
    Optional<AnimalModel> findById(Long id);
}

package com.hotel.pethotel.repository;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {
    Optional<ReservationModel> findById(Long id);
}

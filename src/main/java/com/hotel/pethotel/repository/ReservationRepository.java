package com.hotel.pethotel.repository;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ReservationModel;
import com.hotel.pethotel.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Optional;
@Repository


public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {
    Optional<ReservationModel> findById(Long id);
    List<ReservationModel> findByReservationStatus(ReservationStatus status);
}

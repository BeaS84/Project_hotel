package com.hotel.pethotel.Reservation;

import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.model.AnimalModel;
//import com.hotel.pethotel.model.ReservationModel;
import com.hotel.pethotel.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Optional;
@Repository


public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {

    Optional<ReservationModel> findById(Long id);
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM ReservationModel r " +
            "WHERE r.room.id = :roomId " +
            "AND r.reservationStatus = :reservationStatus " +
            "AND ((r.startDate <= :startDate AND r.endDate >= :startDate) OR " +
            "(r.startDate <= :endDate AND r.endDate >= :endDate) OR " +
            "(r.startDate >= :startDate AND r.endDate <= :endDate))")
    boolean isExistsByRoomAndReservationStatus(
            Long roomId,
            ReservationStatus reservationStatus,
            LocalDate startDate,
            LocalDate endDate
    );
}

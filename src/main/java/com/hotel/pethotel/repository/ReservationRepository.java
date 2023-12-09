package com.hotel.pethotel.repository;

import com.hotel.pethotel.Reservation.ReservationModel;
import com.hotel.pethotel.Reservation.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {
    Optional<ReservationModel> findById(Long id);
    List<ReservationModel> findByReservationStatus(ReservationStatus status);
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
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

package com.hotel.pethotel.repository;

import com.hotel.pethotel.Reservation.ReservationModel;
import com.hotel.pethotel.Reservation.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {
    Optional<ReservationModel> findById(Long id);

    List<ReservationModel> findByReservationStatus(ReservationStatus status);

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM ReservationModel r " +
            "WHERE r.room.id = :roomId " +
            "AND r.reservationStatus IN (:reservationStatuses) " +
            "AND ((r.startDate <= :startDate AND r.endDate >= :startDate) OR " +
            "(r.startDate <= :endDate AND r.endDate >= :endDate) OR " +
            "(r.startDate >= :startDate AND r.endDate <= :endDate))")
    boolean isExistsByRoomAndReservationStatus(
            Long roomId,
            List<ReservationStatus> reservationStatuses,
            LocalDate startDate,
            LocalDate endDate
    );
    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM ReservationModel r " +
            "WHERE r.room.id = :roomId " +
            "AND r.reservationStatus IN (:reservationStatuses) " +
            "AND ((r.startDate <= :startDate AND r.endDate< :startDate))")

    boolean isRoomHasAnyReservation(
            Long roomId,
            List<ReservationStatus> reservationStatuses,
            LocalDate startDate
    );

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
            "FROM ReservationModel r " +
            "WHERE r.room.id = :roomId " +
            "AND r.reservationStatus IN (:reservationStatuses) " +
            "AND ((r.endDate >= :startDate))")

    boolean isRoomHasPresentOrFutureReservation(
            Long roomId,
            List<ReservationStatus> reservationStatuses,
            LocalDate startDate
    );

    @Query("SELECT r FROM ReservationModel r " +
            "WHERE r.animal.id = :animalId " +
            "AND r.reservationStatus IN :statuses " +
            "AND r.endDate >= :currentDate")
    List<ReservationModel> findActiveReservationsForAnimal(
            @Param("animalId") Long animalId,
            @Param("currentDate") LocalDate currentDate,
            @Param("statuses") Set<ReservationStatus> statuses
    );


    @Query("SELECT r FROM ReservationModel r " +
            "WHERE r.animal.id = :animalId " +
            "AND r.reservationStatus = 'PENDING'")
    List<ReservationModel> findPendingReservationsForAnimal(@Param("animalId") Long animalId);

    @Query("SELECT r FROM ReservationModel r " +
            "WHERE r.animal.id = :animalId " +
            "AND r.reservationStatus = :status")
    List<ReservationModel> findReservationsForAnimalByStatus(
            @Param("animalId") Long animalId,
            @Param("status") ReservationStatus status
    );

    @Query("SELECT r FROM ReservationModel r WHERE r.animal.id = :animalId")
    List<ReservationModel> findByAnimalId(@Param("animalId") Long animalId);
}
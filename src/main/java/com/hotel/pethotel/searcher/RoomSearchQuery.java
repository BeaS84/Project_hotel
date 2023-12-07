package com.hotel.pethotel.searcher;

import com.hotel.pethotel.Reservation.ReservationStatus;
import com.hotel.pethotel.model.Standard;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class RoomSearchQuery {
    String selectedAnimalId;
    Standard standard;
    LocalDate reservationStartDate;
    LocalDate reservationEndDate;
    ReservationStatus reservationStatus;
}

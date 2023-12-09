package com.hotel.pethotel.Reservation;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationRequest {
    private Long animalId;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
}

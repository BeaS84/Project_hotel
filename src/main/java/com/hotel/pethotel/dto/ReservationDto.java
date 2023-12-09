package com.hotel.pethotel.dto;

import com.hotel.pethotel.Reservation.ReservationStatus;
import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.model.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class ReservationDto {
    private Long id;
    private Long reservationNumber;
    private LocalDate startDate;
    private LocalDate endDate;

    private BigDecimal price;
    private ReservationStatus reservationStatus;

    private ClientModel owner;
    private RoomModel room;
    private Set<AnimalModel> animals;
}


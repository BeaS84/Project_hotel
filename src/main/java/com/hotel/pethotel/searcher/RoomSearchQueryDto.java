package com.hotel.pethotel.searcher;

import com.hotel.pethotel.model.Standard;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomSearchQueryDto {
    String selectedAnimalId;
    Standard standard;
    LocalDate reservationStartDate;
    LocalDate reservationEndDate;
    //dodano
//    ReservationStatus reservationStatus;
}

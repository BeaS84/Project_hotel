package com.hotel.pethotel.searcher;

import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.Reservation.ReservationStatus;
import com.hotel.pethotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomSearchService {
    private final RoomRepository roomRepository;

    public List<RoomModel> getAvailableRooms(RoomSearchQuery searchQuery) {
        return roomRepository.findAvailableRooms(
                searchQuery.getSelectedAnimalId(),
                searchQuery.getStandard(),
                searchQuery.getReservationStartDate(),
                searchQuery.getReservationEndDate(),
                ReservationStatus.CANCELLED
        );

    }

}
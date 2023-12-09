package com.hotel.pethotel.Searcher;

import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.Reservation.ReservationStatus;
import com.hotel.pethotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
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

    public long getSearchQueryDuration(RoomSearchQuery searchQuery) {
        LocalDate reservationStartDate = searchQuery.getReservationStartDate();
        LocalDate reservationEndDate = searchQuery.getReservationEndDate();
        if (reservationStartDate == null || reservationEndDate == null) {
            return 0;
        }
        Duration duration = Duration.between(reservationStartDate.atStartOfDay(),reservationEndDate.atStartOfDay());
        return duration.toDays();
    }

}
package com.hotel.pethotel.searcher;

import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.model.ReservationStatus;
import com.hotel.pethotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomSearchService {
    private final RoomRepository roomRepository;

    public List<RoomModel> getAvailableRooms(RoomSearchQuery searchQuery) {
        // TODO: zmapowac na DTO
        return roomRepository.findAvailableRooms(
                searchQuery.getSelectedAnimalId(),
                searchQuery.getStandard(),
                searchQuery.getReservationStartDate(),
                searchQuery.getReservationEndDate(),
                ReservationStatus.CANCELLED
        );


//dopisac tu metode
//    public static RoomSearchResult toSearchResult(RoomModel room, LocalDate reservationStartDate, LocalDate reservationEndDate) {
//        RoomSearchResult searchResult = new RoomSearchResult();
//        searchResult.setTotalPrice(room.getCostPerNight().multiply(BigDecimal.valueOf(Duration.between(reservationStartDate, reservationEndDate).toDays())));
//        return searchResult;
    }
}

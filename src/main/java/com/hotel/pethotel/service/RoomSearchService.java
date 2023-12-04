package com.hotel.pethotel.service;

import com.hotel.pethotel.dto.RoomSearchQuery;
import com.hotel.pethotel.model.RoomModel;
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

        //zmapowac na DTO
        return roomRepository.findAvailableRooms(
                searchQuery.getSelectedAnimalId(),
                searchQuery.getStandard(),
                searchQuery.getReservationStartDate(),
                searchQuery.getReservationEndDate()
        );
    }

//dopisac tu metode
//    public static RoomSearchResult toSearchResult(RoomModel room, LocalDate reservationStartDate, LocalDate reservationEndDate) {
//        RoomSearchResult searchResult = new RoomSearchResult();
//        searchResult.setTotalPrice(room.getCostPerNight().multiply(BigDecimal.valueOf(Duration.between(reservationStartDate, reservationEndDate).toDays())));
//        return searchResult;
//    }
}

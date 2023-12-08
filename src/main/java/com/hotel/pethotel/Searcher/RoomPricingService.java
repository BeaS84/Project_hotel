package com.hotel.pethotel.Searcher;

import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
@RequiredArgsConstructor
public class RoomPricingService {
    private final RoomRepository roomRepository;
    public BigDecimal calculateRoomPrice(RoomModel room, long durationInDays) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (durationInDays <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0");
        }
        // Pobierz cenę za noc z obiektu RoomModel
        BigDecimal costPerNight = room.getCostPerNight();
        // Oblicz całkowitą cenę pokoju na podstawie liczby dni
        return costPerNight.multiply(BigDecimal.valueOf(durationInDays));
    }
}

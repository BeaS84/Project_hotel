package com.hotel.pethotel.searcher;

import com.hotel.pethotel.Rooms.RoomModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
public class RoomPrices {
    private RoomModel room;
     private BigDecimal price;

}

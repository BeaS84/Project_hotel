package com.hotel.pethotel.Searcher;

import com.hotel.pethotel.Rooms.RoomModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RoomPrices {
    private RoomModel room;
     private BigDecimal price;

}

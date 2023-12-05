package com.hotel.pethotel.dto;

import com.hotel.pethotel.model.AnimalSize;
import com.hotel.pethotel.model.Standard;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class RoomDto {
    private Long id;
    private String name;
    private String description;

    private Standard standard; //en

    private Set<AnimalSize> animalSize;
    private BigDecimal costPerNight;
    private Boolean active;


}

package com.hotel.pethotel.dto;

import com.hotel.pethotel.model.Sex;
import lombok.Data;

@Data
public class AnimalDto {
    private Long id;
    private String type;
  //  private Integer weight;
    private String name;
    private Sex sex;
}

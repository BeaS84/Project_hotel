package com.hotel.pethotel.dto;

import com.hotel.pethotel.model.Sex;
import com.hotel.pethotel.model.Type;
import lombok.Data;

@Data
public class AnimalDto {
    private Long id;
    private Type type;
    //  private Integer weight;
    private String name;
    private Sex sex;
}

//public class AnimalDto {
//    private Long id;
//    private String type;
//    private String name;
//    private String sex;
//}
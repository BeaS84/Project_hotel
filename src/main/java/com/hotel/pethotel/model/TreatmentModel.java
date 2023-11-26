package com.hotel.pethotel.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TreatmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "animal_id") // wiele leczen do jednego zwierzecia, JoinColumn, klucz obcy id zwierzecia
    private AnimalModel animal;

}

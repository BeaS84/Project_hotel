package com.hotel.pethotel.model;


import com.hotel.pethotel.Reservation.ReservationModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name="animal_model")
public class AnimalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "weight")
    @Enumerated(EnumType.STRING)
    private AnimalSize size; //Beata enum do dyskusji

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne
    private ClientModel client;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private Set<TreatmentModel> treatments;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private Set<ReservationModel> reservations;
}
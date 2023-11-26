package com.hotel.pethotel.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="room_model")
public class RoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "standard")
    private Standard standard; //enum

    @ElementCollection
    @CollectionTable(name = "animal_size", joinColumns = @JoinColumn(name = "room_id"))
    private List<Integer> animalSize;

    @Column(name = "is_active")
    private Boolean active;

    @ManyToMany(mappedBy = "rooms")
    private Set<ReservationModel> reservations;

}

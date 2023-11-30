package com.hotel.pethotel.rooms;


import com.hotel.pethotel.model.AnimalSize;
import com.hotel.pethotel.model.ReservationModel;
import com.hotel.pethotel.model.Standard;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@Table(name="room_model")
public class RoomModel {
    public RoomModel() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "standard")
    @Enumerated(EnumType.STRING)
    private Standard standard; //enum

    @ElementCollection
    @CollectionTable(name = "animal_size", joinColumns = @JoinColumn(name = "room_id"))
    @Enumerated(EnumType.STRING)
    private Set<AnimalSize> animalSize;

    @Column(name = "is_active")
    private Boolean active;

    @ManyToMany(mappedBy = "rooms")
    private Set<ReservationModel> reservations;

}

package com.hotel.pethotel.model;


import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
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

    @Column(nullable = false)
    private BigDecimal costPerNight;

    @Column(name = "is_active")
    private Boolean active;

    @ManyToMany(mappedBy = "rooms")
    private Set<ReservationModel> reservations;

}

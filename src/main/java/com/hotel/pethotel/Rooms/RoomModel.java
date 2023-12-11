package com.hotel.pethotel.Rooms;


import com.hotel.pethotel.model.AnimalSize;
import com.hotel.pethotel.Reservation.ReservationModel;
import com.hotel.pethotel.model.Standard;
import com.hotel.pethotel.model.Type;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@Table(name="room_model")
public class RoomModel {
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
    private Set<AnimalSize> allowedAnimalSizes;

    @ElementCollection
    @CollectionTable(name = "animal_type", joinColumns = @JoinColumn(name = "room_id"))
    @Enumerated(EnumType.STRING)
    private Set<Type> allowedAnimalTypes;

    @Column(nullable = false)
    private BigDecimal costPerNight;

    @Column(name = "is_active")
    private Boolean active;

    private Boolean hasFutureReservation;

//    @ManyToMany(mappedBy = "rooms")
//    private Set<ReservationModel> reservations;
    //zamienilam na 1 do wielu bo jeden pokoj moze yc w wielu rezerwacjach
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<ReservationModel> reservations;

}

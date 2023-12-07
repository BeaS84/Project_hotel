package com.hotel.pethotel.Reservation;


import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
public class ReservationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_number")
    private Long reservationNumber;

    @Column(name = "start_Date")
    private LocalDate startDate;

    @Column(name = "end_Date")
    private LocalDate endDate;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "reservation_status")
    private ReservationStatus reservationStatus;

    @ManyToOne
    @JoinColumn(name = "client_id") //
    private ClientModel client;



//    @ManyToMany
//    @JoinTable(
//            name = "reservation_room",
//            joinColumns = @JoinColumn(name = "reservation_id"),
//            inverseJoinColumns = @JoinColumn(name = "room_id"))
//    private Set<RoomModel> rooms;

    @ManyToOne
    @JoinColumn(name = "room_id") // wiele lączen do jednego pokoju, JoinColumn, klucz obcy id pokoju
    private RoomModel room;


    @ManyToOne
    @JoinColumn(name = "animal_id") // Pojedyncze połączenie do jednego zwierzęcia
    private AnimalModel animal;
}

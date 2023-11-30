package com.hotel.pethotel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("client")
//private final role=client;
public class ClientModel extends UserModel {
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL) //relacja 1 do wielu z AnimalModel, wlasciciel relacji
    private List<AnimalModel> animals;

//    @OneToMany(mappedBy = "reservation_model", cascade = CascadeType.ALL) //relacja 1 do wielu z ReservationModel, wlasciciel relacji
//    private List<ReservationModel> reservations;
}

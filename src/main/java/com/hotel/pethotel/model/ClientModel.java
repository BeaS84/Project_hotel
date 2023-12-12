package com.hotel.pethotel.model;

import com.hotel.pethotel.Reservation.ReservationModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
@DiscriminatorValue("client")

public class ClientModel extends UserModel {
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL) //relacja 1 do wielu z AnimalModel, wlasciciel relacji
    private List<AnimalModel> animals;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ReservationModel> reservations;
}

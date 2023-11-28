package com.hotel.pethotel.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Getter
@Setter
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "phone")
    private Long phone;

    @ManyToMany
    @JoinTable (name = "user_x_roles")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<RoleModel> roles;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //relacja 1 do wielu z AnimalModel, wlasciciel relacji
//    private List<AnimalModel> animals;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //relacja 1 do wielu z ReservationModel, wlasciciel relacji
//    private List<ReservationModel> reservations;


}
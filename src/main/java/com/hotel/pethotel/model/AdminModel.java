package com.hotel.pethotel.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@DiscriminatorValue("admin")
public class AdminModel extends UserModel{
}

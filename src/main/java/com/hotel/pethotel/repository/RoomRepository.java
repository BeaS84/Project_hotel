package com.hotel.pethotel.repository;

import com.hotel.pethotel.model.RoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomModel, Long> {
}

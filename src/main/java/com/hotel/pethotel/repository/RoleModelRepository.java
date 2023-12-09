package com.hotel.pethotel.repository;

import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleModelRepository extends JpaRepository<RoleModel, Long> {

    Optional<RoleModel> findByName(String name);
}

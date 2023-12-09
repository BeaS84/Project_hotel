package com.hotel.pethotel.service;

import com.hotel.pethotel.model.ReservationModel;
import com.hotel.pethotel.model.ReservationStatus;
import com.hotel.pethotel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public List<ReservationModel> getAllReservationList() {
        return reservationRepository.findAll();
    }
    public List<ReservationModel> getReservationsByStatus(String status) {
        return reservationRepository.findByReservationStatus(ReservationStatus.valueOf(status));
    }
}
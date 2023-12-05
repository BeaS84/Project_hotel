package com.hotel.pethotel.mapper;

import com.hotel.pethotel.dto.ReservationDto;
import com.hotel.pethotel.model.ReservationModel;

public class ReservationMapper {
    public static ReservationDto toReservationDto(ReservationModel reservationModel){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservationModel.getId());
        reservationDto.setReservationNumber(reservationModel.getReservationNumber());
        reservationDto.setStartDate(reservationModel.getStartDate());
        reservationDto.setEndDate(reservationModel.getEndDate());
        reservationDto.setPrice(reservationModel.getPrice());
        reservationDto.setReservationStatus(reservationModel.getReservationStatus());
        reservationDto.setOwner(reservationModel.getOwner());
        reservationDto.setRoom(reservationModel.getRoom());
        reservationDto.setAnimals(reservationModel.getAnimals());
        return reservationDto;
    }
    public static ReservationModel reservationModel(ReservationDto reservationDto){
        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setId(reservationDto.getId());
        reservationModel.setReservationNumber(reservationDto.getReservationNumber());
        reservationModel.setStartDate(reservationDto.getStartDate());
        reservationModel.setEndDate(reservationDto.getEndDate());
        reservationModel.setPrice(reservationDto.getPrice());
        reservationModel.setReservationStatus(reservationDto.getReservationStatus());
        reservationModel.setOwner(reservationDto.getOwner());
        reservationModel.setRoom(reservationDto.getRoom());
        reservationModel.setAnimals(reservationDto.getAnimals());
        return reservationModel;
    }
}

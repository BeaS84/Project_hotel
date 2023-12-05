package com.hotel.pethotel.mapper;

import com.hotel.pethotel.dto.RoomDto;
import com.hotel.pethotel.model.RoomModel;

public class RoomMapper {
    public static RoomDto toRoomDto(RoomModel roomModel){
        RoomDto roomDto = new RoomDto();
        roomDto.setName(roomModel.getName());
        roomDto.setDescription(roomModel.getDescription());
        roomDto.setStandard(roomModel.getStandard());
        roomDto.setCostPerNight(roomModel.getCostPerNight());
        roomDto.setActive(roomModel.getActive());
        return roomDto;
    }
    public static RoomModel toRoomModel (RoomDto roomDto){
        RoomModel roomModel = new RoomModel();
        roomModel.setName(roomDto.getName());
        roomModel.setDescription(roomDto.getDescription());
        roomModel.setStandard(roomDto.getStandard());
        roomModel.setCostPerNight(roomDto.getCostPerNight());
        roomModel.setActive(roomDto.getActive());
        return roomModel;
    }


}

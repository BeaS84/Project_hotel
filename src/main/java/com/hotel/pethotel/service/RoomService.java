package com.hotel.pethotel.service;
import com.hotel.pethotel.model.RoomModel;
import com.hotel.pethotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    //wstrzykujemy repozytorium room
    private final RoomRepository roomRepository;

    //dodajemy metode dodawania pokoju - korzystamy z gotowej
    public void addRoom(RoomModel room){
        //pobieramy to z repozytorium i robimy save
        roomRepository.save(room);
    }

    //wyswietlenie listy pokoi
    public List<RoomModel> getRoomList(){
        //pobieramy to z repozytorium i robimy find all
        return roomRepository.findAll();
    }
//poprawic na optionala

//    public RoomModel getRoomById(Long id) {
//        return roomRepository.findById(id).orElse(null);
//    }
    public RoomModel getRoomById(Long id) {
        Optional<RoomModel> optionalRoomModel = roomRepository.findById(id);
        return optionalRoomModel.orElseThrow(()-> new NoSuchElementException("Room not found with id: "+id));
    }


    public void saveEditRoom(RoomModel editRoom) {
        roomRepository.save(editRoom);
    }



}

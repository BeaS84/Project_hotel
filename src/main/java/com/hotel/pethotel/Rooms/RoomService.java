package com.hotel.pethotel.Rooms;
import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public RoomModel getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }
    public void saveEditRoom(RoomModel editRoom) {
        roomRepository.save(editRoom);
    }



}

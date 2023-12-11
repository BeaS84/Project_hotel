package com.hotel.pethotel.Rooms;
import com.hotel.pethotel.Reservation.ReservationModel;
import com.hotel.pethotel.Reservation.ReservationStatus;
import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.repository.ReservationRepository;
import com.hotel.pethotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.hotel.pethotel.Reservation.ReservationStatus.CONFIRMED;

@Service
@RequiredArgsConstructor
public class RoomService {
    //wstrzykujemy repozytorium room
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

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

//    public boolean isRoomAvailable(Long roomId) {
//        // Pobieramy pokój po ID
//        RoomModel room = roomRepository.findById(roomId).orElse(null);
//
//        // Jeżeli pokój nie istnieje, to oznaczamy go jako niedostępny
//        if (room == null) {
//            return false;
//        }
//        //Zwraca true, jeśli nie ma potwierdzonych (CONFIRMED) ani oczekujących (PENDING) rezerwacji w danym zakresie dat/false, jeśli pokój nie istnieje lub ma rezerwację w danym zakresie dat.
//        return !reservationRepository.isRoomHasAnyReservation(
//                roomId,
//                List.of(ReservationStatus.CONFIRMED,ReservationStatus.PENDING),LocalDate.now()
//        );
//    }
public boolean isRoomIsReservedNowOrInFuture(Long roomId) {
    RoomModel room = roomRepository.findById(roomId).orElse(null);
    if (room == null) {
        return false;
    }

    boolean isReserved = reservationRepository.isRoomHasPresentOrFutureReservation(
            roomId,
            List.of(ReservationStatus.CONFIRMED, ReservationStatus.PENDING),
            LocalDate.now());

    room.setHasFutureReservation(isReserved);
    roomRepository.save(room);

    return isReserved;
}

}

package com.hotel.pethotel.Rooms;
import com.hotel.pethotel.Exceptions.RoomValidationException;
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
//    public void addRoom(RoomModel room){
//        //pobieramy to z repozytorium i robimy save
//        roomRepository.save(room);
//    }

    public void addRoom(RoomModel room){
        try {
            validateRoomFields(room);
            roomRepository.save(room);
        } catch (RoomValidationException e) {
            throw new RuntimeException("Error during room creation: " + e.getMessage(), e);
        }
    }
//W tej wersji kodu dodano prywatną metodę validateRoomFields-sprawdzamy czy wymagane pola są dodane , czy wymagane pola są ustawione.
// Jeśli któreś z pól nie spełnia warunków-rzucamyy RoomValidationException.
// Ta metoda bedzie wywoływana w addRoom przed próbą zapisu pokoju do repozytorium.
    private void validateRoomFields(RoomModel room) {
        if (room.getName() == null || room.getName().trim().isEmpty()) {
            throw new RoomValidationException("Name is required");
        }

        if (room.getStandard() == null) {
            throw new RoomValidationException("Standard is required");
        }

        if (room.getAllowedAnimalSizes() == null || room.getAllowedAnimalSizes().isEmpty()) {
            throw new RoomValidationException("Animal Sizes are required");
        }

        if (room.getAllowedAnimalTypes() == null || room.getAllowedAnimalTypes().isEmpty()) {
            throw new RoomValidationException("Animal Types are required");
        }

        if (room.getCostPerNight() == null) {
            throw new RoomValidationException("Cost per Night is required");
        }
    }


    //wyswietlenie listy pokoi
    public List<RoomModel> getRoomList(){
        //pobieramy to z repozytorium i robimy find all
        return roomRepository.findAll();
    }

//    public RoomModel getRoomById(Long id) {
//        return roomRepository.findById(id).orElse(null);
//    }

    public RoomModel getRoomById(Long id) {
            validateRoomId(id);
            return roomRepository.findById(id).orElse(null);
    }

    private void validateRoomId(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("Invalid room ID");
        }
    }


    public void saveEditRoom(RoomModel editRoom) {
        validateEditRoom(editRoom);
        roomRepository.save(editRoom);
    }
    private void validateEditRoom(RoomModel editRoom) {
        if (editRoom == null || editRoom.getId() == null || editRoom.getId() <= 0) {
            throw new RuntimeException("Invalid room for editing");
        }

        if (editRoom.getName() == null || editRoom.getName().trim().isEmpty()) {
            throw new RuntimeException("Name field cannot be empty");
        }
        if (editRoom.getStandard() == null) {
            throw new RoomValidationException("Standard field cannot be empty");
        }

        if (editRoom.getAllowedAnimalSizes() == null || editRoom.getAllowedAnimalSizes().isEmpty()) {
            throw new RoomValidationException("Animal Sizes field cannot be empty");
        }

        if (editRoom.getAllowedAnimalTypes() == null || editRoom.getAllowedAnimalTypes().isEmpty()) {
            throw new RoomValidationException("Animal Types field cannot be empty");
        }

        if (editRoom.getCostPerNight() == null) {
            throw new RoomValidationException("Cost per Night field cannot be empty");
        }
    }

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

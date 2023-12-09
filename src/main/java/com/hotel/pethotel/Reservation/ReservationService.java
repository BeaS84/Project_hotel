package com.hotel.pethotel.Reservation;

import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
//    private final ClientRepository clientRepository;
//    private final AnimalRepository animalRepository;


    public ReservationModel createReservation(ClientModel client, AnimalModel animal, RoomModel room, LocalDate startDate, LocalDate endDate) {
       // boolean isRoomAvailable = roomRepository.isRoomAvailable(room.getId(), startDate, endDate);
      //  boolean isCanceledReservationExists = reservationRepository.isExistsByClientAndAnimalAndRoomAndReservationStatus(
//                client, animal, room, ReservationStatus.CANCELLED);

        boolean isRoomAvailable = roomRepository.isRoomAvailable(room.getId(), startDate, endDate);
        boolean isReservationCancelled = reservationRepository.isExistsByRoomAndReservationStatus(room.getId(), ReservationStatus.CANCELLED, startDate, endDate);

        if (isReservationCancelled) {
            // TODO: Tutaj możesz obsłużyć sytuację, gdy istnieje już anulowana rezerwacja
            System.out.println("Istnieje anulowana rezerwacja dla tego klienta, zwierzaka i pokoju. Tworzę nową rezerwację.");
        }


//        if (!isRoomAvailable) {
////            TODO: wyjatek - pokoj zajety
//            System.out.println("tutaj pokoj jest juz zajęty");
//            throw new RuntimeException("zmienic na cos innego");
//        }
        if(isRoomAvailable ||isReservationCancelled ){

        ReservationModel reservation = new ReservationModel();
        reservation.setClient(client);
        reservation.setAnimal(animal);
        reservation.setRoom(room);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        // Ustawienie ceny koncowej:
        reservation.setPrice(calculateReservationPrice(startDate, endDate, room.getCostPerNight()));
        // Ustawienie statusu rezerwacji- domyslnie ma byc PENDING
        reservation.setReservationStatus(ReservationStatus.PENDING);
        return reservationRepository.save(reservation);
    } else {
        // TODO: Obsługa sytuacji, gdy pokój jest zajęty
        System.out.println("Pokój jest już zajęty.");
        throw new RuntimeException("Pokój jest zajęty. Zmień na coś innego.");
    }
    }
 //przeliczanie ceny -
    public BigDecimal calculateReservationPrice(LocalDate startDate, LocalDate endDate, BigDecimal costPerNight) {
        // Implementacja logiki obliczania ceny rezerwacji -cena zależy od liczby dni pobytu
        long numberOfNights = ChronoUnit.DAYS.between(startDate, endDate);
        return costPerNight.multiply(BigDecimal.valueOf(numberOfNights));
    }



    public List<ReservationModel> getAllReservationList() {
        return reservationRepository.findAll();
    }
}

//
//    private ClientModel getClientById(Long clientId) {
//        // Implementacja pobierania klienta na podstawie ID
//        return clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
//    }
//
//    private AnimalModel getAnimalById(Long animalId) {
//        // Implementacja pobierania zwierzęcia na podstawie ID
//        // Może to być zrealizowane na przykład za pomocą AnimalRepository
//        return animalRepository.findById(animalId).orElseThrow(() -> new RuntimeException("Animal not found"));
//    }
//    private RoomModel getRoomById(Long roomId) {
//        // Implementacja pobierania pokoju na podstawie ID
//        // Może to być zrealizowane na przykład za pomocą RoomRepository
//        return roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
//    }

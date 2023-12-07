package com.hotel.pethotel.Reservation;

import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.model.AnimalModel;
import com.hotel.pethotel.model.ClientModel;
import com.hotel.pethotel.repository.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReservationServiceTest {

    @Test
    public void testReservationCreation() {
        // setup
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);

        ReservationService service = new ReservationService(reservationRepository, roomRepository);

        ClientModel client = new ClientModel();
        AnimalModel animal= new AnimalModel();
        RoomModel room =  new RoomModel();
        room.setCostPerNight(BigDecimal.ONE);
        ReservationModel expectedReservation = new ReservationModel();

        // preparujesz mocki (pokoj jest dostepny)
        Mockito.when(roomRepository.isRoomAvailable(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(true);

        Mockito.when(reservationRepository.save(Mockito.any()))
                .thenReturn(expectedReservation);

        // miecho - testujesz
        ReservationModel reservation = service.createReservation(
                client,
                animal,
                room,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 13)
        );


        Assertions.assertEquals(expectedReservation, reservation);
    }

    @Test
    public void testReservationCreationWhenRoomIsNotAvailable() {
        // setup
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);

        ReservationService service = new ReservationService(reservationRepository, roomRepository);

        ClientModel client = new ClientModel();
        AnimalModel animal= new AnimalModel();
        RoomModel room =  new RoomModel();
        ReservationModel expectedReservation = new ReservationModel();

        // preparujesz mocki (pokoj jest dostepny)
        Mockito.when(roomRepository.isRoomAvailable(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(false);

        // miecho - testujesz
        Assertions.assertThrows(RuntimeException.class, () -> {
            ReservationModel reservation = service.createReservation(
                    client,
                    animal,
                    room,
                    LocalDate.of(2023, 1, 1),
                    LocalDate.of(2023, 1, 13)
            );
        });
    }

    @Test
    public void testPriceCalculation() {
        // setup
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);

        ReservationService service = new ReservationService(reservationRepository, roomRepository);

        BigDecimal expectedPrice = BigDecimal.valueOf(1300);

        // miecho - testujesz
        BigDecimal estimatedPrice = service.calculateReservationPrice(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 13),
                BigDecimal.valueOf(100)
        );

        // assert - sprawdzasz czy ok
        Assertions.assertEquals(estimatedPrice, expectedPrice);
    }

    @Test
    public void testPriceCalculationForOneDay() {
        // setup
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);

        ReservationService service = new ReservationService(reservationRepository, roomRepository);

        BigDecimal expectedPrice = BigDecimal.valueOf(100);

        // miecho - testujesz
        BigDecimal estimatedPrice = service.calculateReservationPrice(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 1),
                BigDecimal.valueOf(100)
        );

        // assert - sprawdzasz czy ok
        Assertions.assertEquals(estimatedPrice, expectedPrice);
    }
}

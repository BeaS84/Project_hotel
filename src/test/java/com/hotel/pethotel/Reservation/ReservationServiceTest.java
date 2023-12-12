package com.hotel.pethotel.Reservation;

import com.hotel.pethotel.Rooms.RoomModel;
import com.hotel.pethotel.model.*;
import com.hotel.pethotel.repository.AnimalRepository;
import com.hotel.pethotel.repository.ReservationRepository;
import com.hotel.pethotel.repository.RoomRepository;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    ReservationRepository reservationRepository;
    RoomRepository roomRepository;
    AnimalRepository animalRepository;

    ReservationService reservationService;

    @BeforeEach
    void setUp() {
    MockitoAnnotations.openMocks(this);
        reservationRepository = Mockito.mock(ReservationRepository.class);
        roomRepository = Mockito.mock(RoomRepository.class);
        animalRepository = Mockito.mock(AnimalRepository.class);
        reservationService = new ReservationService(reservationRepository,roomRepository,animalRepository);
    }

    @Test
    void shouldCreateReservation() {
        //given
        ClientModel client= getClient();
        AnimalModel animal = getAnimalModel();
        RoomModel room = getRoomModel();
        room.setId(1L);

        LocalDate startDate=LocalDate.now();
        LocalDate endDate=LocalDate.now().plusDays(1);
        Mockito.when(reservationRepository
                .isExistsByRoomAndReservationStatus(1L, List.of(ReservationStatus.CANCELLED)
                        ,startDate,endDate))
                .thenReturn(false);
        Mockito.when(roomRepository
                        .isRoomAvailable(1L,startDate,endDate))
                .thenReturn(true);

        //when
        ReservationModel actualResult
                = reservationService.createReservation(client, animal, room, startDate, endDate);

        //then
        Assertions.assertThat(actualResult.getReservationStatus())
                .isEqualTo(ReservationStatus.PENDING);
    }

    private static RoomModel getRoomModel() {
        RoomModel room=new RoomModel();
        RoomModel.builder().reservations(Set.of()).active(true).id(1L)
                .costPerNight(BigDecimal.valueOf(100.0)).build();
        return room;
    }

    private static AnimalModel getAnimalModel() {
        AnimalModel animal=new AnimalModel();
        AnimalModel.builder().sex(Sex.BOY).reservations(Set.of()).size(AnimalSize.SMALL)
                .type(Type.DOG).build();
        return animal;
    }

    private static ClientModel getClient() {
        ClientModel client = new ClientModel();
        ClientModel.builder().animals(List.of()).build();
        return client;
    }
}
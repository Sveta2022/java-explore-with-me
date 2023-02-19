package ru.practicum.main_service.requests.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.main_service.category.model.CategoryEvent;
import ru.practicum.main_service.event.dao.EventStorage;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.model.StateEvent;
import ru.practicum.main_service.requests.dao.RequestStorage;
import ru.practicum.main_service.requests.dto.ParticipationRequestDto;
import ru.practicum.main_service.requests.model.ParticipationRequest;
import ru.practicum.main_service.requests.model.RequestEventStatus;
import ru.practicum.main_service.user.dao.UserStorage;
import ru.practicum.main_service.user.model.Location;
import ru.practicum.main_service.user.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestServiceImplTest {

    @InjectMocks
    private RequestServiceImpl requestService;
    @Mock
    private RequestStorage requestStorage;
    @Mock
    private EventStorage eventStorage;
    @Mock
    private UserStorage userStorage;
    private User user1;
    private User user2;
    private ParticipationRequest request1;
    private Event event1;


    @BeforeEach
    void start() {
        user1 = User.builder()
                .id(1L)
                .name("nameUser1")
                .email("email1@mail.ru")
                .build();
        user2 = User.builder()
                .id(2L)
                .name("nameUser2")
                .email("email2@mail.ru")
                .build();

        event1 = Event.builder()
                .id(1L)
                .title("title1")
                .annotation("annotation1tomaketwentysymbol")
                .category(CategoryEvent.builder()
                        .id(1L)
                        .name("categoryname")
                        .build())
                .description("description1tomaketwentysymbol")
                .eventDate(LocalDateTime.of(2023, 11, 12, 15, 30, 00))
                .location(Location.builder()
                        .lat(56.2)
                        .lon(66.9)
                        .build())
                .paid(true)
                .participantLimit(256)
                .createdOn(LocalDateTime.now())
                .initiator(user2)
                .publishedOn(LocalDateTime.of(2023, 11, 12, 10, 30, 00))
                .requestModeration(false)
                .views(2L)
                .build();

        request1 = ParticipationRequest.builder()
                .id(1L)
                .requester(user1)
                .event(event1)
                .created(LocalDateTime.of(2023,9,10,15,30,00))
                .status(RequestEventStatus.CONFIRMED)
                .build();
    }

    @Test
    void create() {
        Long eventId = 1L;
        Long userId = 1L;
        when(requestStorage.findByEventIdAndRequesterId(eventId, userId)).thenReturn(null);
        when(userStorage.findById(userId)).thenReturn(Optional.of(user1));
        when(eventStorage.findById(eventId)).thenReturn(Optional.of(event1));
        event1.setStateEvent(StateEvent.PUBLISHED);
        when(requestStorage.save(any())).thenReturn(request1);

        ParticipationRequestDto participationRequestDto = requestService.create(userId, eventId);
        assertEquals(1, participationRequestDto.getId());

    }
}
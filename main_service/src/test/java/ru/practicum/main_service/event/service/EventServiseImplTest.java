package ru.practicum.main_service.event.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.client.EventClient;
import ru.practicum.main_service.category.model.CategoryEvent;
import ru.practicum.main_service.event.dao.CommentStorage;
import ru.practicum.main_service.event.dao.EventStorage;
import ru.practicum.main_service.event.dto.event.EventFullDto;
import ru.practicum.main_service.event.dto.event.EventShortDto;
import ru.practicum.main_service.event.dto.event.NewEventDto;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.model.StateEvent;
import ru.practicum.main_service.exception.ConflictException;
import ru.practicum.main_service.requests.dao.RequestStorage;
import ru.practicum.main_service.requests.model.RequestEventStatus;
import ru.practicum.main_service.user.dao.UserStorage;
import ru.practicum.main_service.user.model.Location;
import ru.practicum.main_service.user.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiseImplTest {

    @InjectMocks
    private EventServiseImpl eventServise;
    @Mock
    private EventStorage eventStorage;
    @Mock
    private UserStorage userStorage;
    @Mock
    private CommentStorage commentStorage;
    @Mock
    private RequestStorage requestStorage;
    @Mock
    private EventClient eventClient;
    private User user1;
    private NewEventDto newEventDto1;
    private Event event1;


    @BeforeEach
    void start() {
        newEventDto1 = NewEventDto.builder()
                .eventId(1L)
                .annotation("annotation1tomaketwentysymbol")
                .category(1L)
                .description("description1tomaketwentysymbol")
                .eventDate("2023-11-12 15:30:00")
                .location(NewEventDto.LocationDto.builder()
                        .lat(56.2)
                        .lon(66.9)
                        .build())
                .paid(true)
                .participantLimit(256)
                .requestModeration(false)
                .title("title1")
                .build();
        user1 = new User(1L, "userName1", "userEmail1");
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
                .initiator(user1)
                .publishedOn(LocalDateTime.of(2023, 11, 12, 10, 30, 00))
                .requestModeration(false)
                .views(2L)
                .build();
    }

    @Test
    void create() {
        when(userStorage.findById(user1.getId())).thenReturn(Optional.of(user1));
        when(eventStorage.save(any())).thenReturn(event1);
        eventServise.create(1L, newEventDto1);

    }

    @Test
    void create_with_not_valid_date() {
        newEventDto1.setEventDate("2022-12-12 15:30:00");
        when(userStorage.findById(user1.getId())).thenReturn(Optional.of(user1));
        ConflictException conflictException = assertThrows(ConflictException.class,
                () -> eventServise.create(1L, newEventDto1));
        assertEquals("дата и время на которые намечено событие не может быть раньше, " +
                "чем через два часа от текущего момента", conflictException.getMessage());
    }

    @Test
    void getAllEventsByUser() {
        when(userStorage.findById(user1.getId())).thenReturn(Optional.of(user1));
        Pageable pageable = PageRequest.of(
                10 / 1,
                1);
        when(eventStorage.findAllByInitiatorId(user1.getId(), pageable)).thenReturn(List.of(event1));
        when(requestStorage.countParticipationRequestByEventIdAndStatus(event1.getId(), RequestEventStatus.CONFIRMED)).thenReturn(25);
        List<EventShortDto> eventsByUser = eventServise.getAllEventsByUser(user1.getId(), 10, 1);
        assertEquals(1, eventsByUser.size());

    }

    @Test
    void update() {
        when(eventStorage.findById(event1.getId())).thenReturn(Optional.of(event1));

        NewEventDto updateEventDto = NewEventDto.builder()
                .eventId(2L)
                .annotation("annotationUpdateTomaketwentysymbol")
                .category(1L)
                .description("descriptionUpdateTomaketwentysymbol")
                .eventDate("2023-11-12 16:30:00")
                .location(NewEventDto.LocationDto.builder()
                        .lat(60.2)
                        .lon(75.9)
                        .build())
                .paid(true)
                .participantLimit(269)
                .requestModeration(false)
                .title("titleUpdate")
                .build();
        Event updateEvent =
                Event.builder()
                        .id(1L)
                        .title("titleUpdate")
                        .annotation("annotation1tomaketwentysymbol")
                        .category(CategoryEvent.builder()
                                .id(1L)
                                .name("categoryname")
                                .build())
                        .description("descriptionUpdateTomaketwentysymbol")
                        .eventDate(LocalDateTime.of(2023, 11, 12, 16, 30, 00))
                        .location(Location.builder()
                                .lat(60.2)
                                .lon(75.9)
                                .build())
                        .paid(true)
                        .participantLimit(269)
                        .createdOn(LocalDateTime.now())
                        .initiator(user1)
                        .publishedOn(LocalDateTime.of(2023, 11, 12, 10, 30, 00))
                        .requestModeration(false)
                        .views(2L)
                        .build();
        when(eventStorage.save(any())).thenReturn(updateEvent);
        EventFullDto update = eventServise.update(updateEventDto, event1.getId());
        assertEquals("titleUpdate", update.getTitle());

    }

    @Test
    void getEventByIdByCreator() {
        when(eventStorage.findByIdAndInitiatorId(anyLong(), anyLong())).thenReturn(event1);
        when(requestStorage.countParticipationRequestByEventIdAndStatus(event1.getId(), RequestEventStatus.CONFIRMED)).thenReturn(25);
        EventFullDto eventByIdByCreator = eventServise.getEventByIdByCreator(user1.getId(), event1.getId());
        assertEquals(1L, event1.getId());
    }

    @Test
    void getEventById() {
        when(eventStorage.findById(event1.getId())).thenReturn(Optional.of(event1));
        event1.setStateEvent(StateEvent.PUBLISHED);
        String path = "/stats?start={start}&end={end}&uris={uris}&unique={unique}";
        Map<String, Object> parameters = Map.of(
                "start", LocalDateTime.of(2020, 9, 1, 0, 0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                "end", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                "uris", List.of("/events/" + event1.getId()).get(0),
                "unique", false
        );
        ResponseEntity<Object> response = new ResponseEntity<>(
                "some response body",
                HttpStatus.OK
        );
        when(eventClient.getViews(any(), any(), anyList(), anyBoolean())).thenReturn(response);
        when(requestStorage.countParticipationRequestByEventIdAndStatus(event1.getId(), RequestEventStatus.CONFIRMED))
                .thenReturn(25);
        EventFullDto eventById = eventServise.getEventById(event1.getId());
        assertEquals(1L, eventById.getId());
    }

    @Test
    void getEventById_with_wrong_StateEvent() {
        when(eventStorage.findById(event1.getId())).thenReturn(Optional.of(event1));
        event1.setStateEvent(StateEvent.PENDING);
        ConflictException conflictException = assertThrows(ConflictException.class,
                () -> eventServise.getEventById(event1.getId()));
        assertEquals("Cобытие должно быть опубликовано", conflictException.getMessage());
    }
}
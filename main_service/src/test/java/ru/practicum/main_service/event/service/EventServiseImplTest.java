package ru.practicum.main_service.event.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.main_service.category.dao.CategoryStorage;
import ru.practicum.main_service.client.EventClient;
import ru.practicum.main_service.event.dao.EventStorage;
import ru.practicum.main_service.event.dto.NewEventDto;
import ru.practicum.main_service.requests.dao.RequestStorage;
import ru.practicum.main_service.user.dao.UserStorage;
import ru.practicum.main_service.user.model.User;

@ExtendWith(MockitoExtension.class)
class EventServiseImplTest {

    @InjectMocks
    private EventServiseImpl eventServise;
    @Mock
    private EventStorage eventStorage;
    @Mock
    private UserStorage userStorage;
    @Mock
    private RequestStorage requestStorage;
    @Mock
    private CategoryStorage categoryStorage;
    @Mock
    private EventClient eventClient;

    private User user1;
    private NewEventDto newEventDto1;

    @BeforeEach
    void start() {
    }

    @Test
    void create() {
    }

    @Test
    void getAllEventsByUser() {
    }

    @Test
    void update() {
    }

    @Test
    void publishByAdmin() {
    }

    @Test
    void getEventByIdByCreator() {
    }

    @Test
    void getEventsWithFilter() {
    }

    @Test
    void getEventById() {
    }

    @Test
    void updateByCreator() {
    }

    @Test
    void getEventsByParametrs() {
    }

    @Test
    void rejectByAdmin() {
    }

    @Test
    void getRequestEventByUser() {
    }

    @Test
    void confirmRequestForEvent() {
    }

    @Test
    void rejectRequestForEvent() {
    }

    @Test
    void cancelByCreator() {
    }
}
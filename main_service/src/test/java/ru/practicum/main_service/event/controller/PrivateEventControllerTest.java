package ru.practicum.main_service.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.main_service.event.dto.event.EventFullDto;
import ru.practicum.main_service.event.dto.event.NewEventDto;
import ru.practicum.main_service.event.service.EventService;
import ru.practicum.main_service.user.model.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = PrivateEventController.class)
class PrivateEventControllerTest {

    @MockBean
    private EventService eventServise;
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private User user1;
    private NewEventDto newEventDto1;
    private EventFullDto eventFullDto1;

    @Autowired
    public PrivateEventControllerTest(EventService eventServise, MockMvc mockMvc, ObjectMapper mapper) {
        this.eventServise = eventServise;
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

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
        eventFullDto1 = EventFullDto.builder()
                .id(1L)
                .title("title1")
                .annotation("annotation1tomaketwentysymbol")
                .category(EventFullDto.CategoryDto.builder()
                        .id(1L)
                        .name("categoryname")
                        .build())
                .description("description1tomaketwentysymbol")
                .eventDate(LocalDateTime.of(2023, 11, 12, 15, 30, 00))
                .location(EventFullDto.LocationDto.builder()
                        .lat(56.2)
                        .lon(66.9)
                        .build())
                .paid(true)
                .participantLimit(256)
                .createdOn(LocalDateTime.now())
                .initiator(EventFullDto.UserShortDto.builder()
                        .id(3L)
                        .name("userShortDtoName")
                        .build())
                .publishedOn(LocalDateTime.of(2023, 11, 12, 10, 30, 00))
                .requestModeration(false)
                .views(2L)
                .build();
    }

    @Test
    @SneakyThrows
    void create() {
        Long userId = 1L;
        when(eventServise.create(anyLong(), any())).thenReturn(eventFullDto1);
        String result = mockMvc.perform(post("/users/{userId}/events", userId)
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(newEventDto1)))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(mapper.writeValueAsString(eventFullDto1), result);
    }
}
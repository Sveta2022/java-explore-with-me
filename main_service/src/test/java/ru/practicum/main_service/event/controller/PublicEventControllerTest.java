package ru.practicum.main_service.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.client.EventClient;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.service.EventService;


import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = PublicEventController.class)
class PublicEventControllerTest {

    @MockBean
    private EventService eventServise;
    @MockBean
    private EventClient eventClient;
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private EventFullDto eventFullDto1;

    @Autowired
    public PublicEventControllerTest(EventService eventServise, MockMvc mockMvc, ObjectMapper mapper, EventClient eventClient) {
        this.eventServise = eventServise;
        this.mockMvc = mockMvc;
        this.mapper = mapper;
        this.eventClient = eventClient;
    }

    @BeforeEach
    void start() {
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
    void getEventById() {
        Long eventId = 1L;
        ResponseEntity<Object> response = new ResponseEntity<>(
                "some response body",
                HttpStatus.OK
        );
        when(eventClient.createEndPointHit(any())).thenReturn(response);
        when(eventServise.getEventById(eventId)).thenReturn(eventFullDto1);

        mockMvc.perform(get("/events/{id}", eventId))
                .andDo(print())
                .andExpect(status().isOk());
        verify(eventServise, times(1)).getEventById(eventId);

    }
}
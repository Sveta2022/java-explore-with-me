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
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.service.EventService;

import java.time.LocalDateTime;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = AdminEventController.class)
class AdminEventControllerTest {

    @MockBean
    private EventService eventServise;
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private EventFullDto eventFullDto1;

    @Autowired
    public AdminEventControllerTest(EventService eventServise, MockMvc mockMvc, ObjectMapper mapper) {
        this.eventServise = eventServise;
        this.mockMvc = mockMvc;
        this.mapper = mapper;
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

//    @Test
//    @SneakyThrows
//    void publishByAdmin() {
//        Long eventId = 1L;
//        when(eventServise.publishByAdmin(eventId)).thenReturn(eventFullDto1);
//        mockMvc.perform(patch("/admin/events/{eventId}/publish",eventId))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//        verify(eventServise,times(1)).publishByAdmin(eventId);
//
//    }
}
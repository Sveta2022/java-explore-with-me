package ru.practicum.main_service.requests.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.main_service.requests.dto.ParticipationRequestDto;
import ru.practicum.main_service.requests.model.RequestEventStatus;
import ru.practicum.main_service.requests.service.RequestService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = RequestController.class)
class RequestControllerTest {

    @MockBean
    private RequestService requestService;
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private ParticipationRequestDto requestDto;

    @Autowired
    public RequestControllerTest(RequestService requestService, MockMvc mockMvc, ObjectMapper mapper) {
        this.requestService = requestService;
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @BeforeEach
    void start() {
        requestDto = ParticipationRequestDto.builder()
                .id(1L)
                .requester(2L)
                .created("created1")
                .event(1L)
                .status(RequestEventStatus.PENDING)
                .build();
    }


    @Test
    @SneakyThrows
    void addRequest() {
        Long userId = 1L;
        Long eventId = 1L;

        when(requestService.create(anyLong(), anyLong())).thenReturn(requestDto);

        mockMvc.perform(post("/users/{userId}/requests", userId)
                        .param("eventId", "1"))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(requestService, times(1)).create(userId, eventId);
    }
}
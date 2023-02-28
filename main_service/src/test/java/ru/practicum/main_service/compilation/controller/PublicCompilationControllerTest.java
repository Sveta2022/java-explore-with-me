package ru.practicum.main_service.compilation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.main_service.compilation.dto.CompilationDto;
import ru.practicum.main_service.compilation.seervice.CompilationService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = PublicCompilationController.class)
class PublicCompilationControllerTest {

    @MockBean
    private CompilationService compilationService;
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private CompilationDto compilationDto;

    @Autowired
    public PublicCompilationControllerTest(CompilationService compilationService, MockMvc mockMvc, ObjectMapper mapper) {
        this.compilationService = compilationService;
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @BeforeEach
    void start() {
        compilationDto = CompilationDto.builder()
                .id(1L)
                .build();
    }

    @Test
    @SneakyThrows
    void getCompilationById() {
        Long compId = 1L;

        when(compilationService.getCompilationDtoById(anyLong())).thenReturn(compilationDto);
        mockMvc.perform(get("/compilations/{compId}", compId))
                .andDo(print())
                .andExpect(status().isOk());

        verify(compilationService, times(1)).getCompilationDtoById(compId);

    }
}
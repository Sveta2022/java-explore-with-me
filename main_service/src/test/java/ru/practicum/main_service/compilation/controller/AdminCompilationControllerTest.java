package ru.practicum.main_service.compilation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.practicum.main_service.compilation.dto.CompilationDto;
import ru.practicum.main_service.compilation.seervice.CompilationService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = AdminCompilationController.class)
class AdminCompilationControllerTest {

    @MockBean
    private CompilationService compilationService;
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private CompilationDto compilationDto;

    @Autowired
    public AdminCompilationControllerTest(CompilationService compilationService, MockMvc mockMvc, ObjectMapper mapper) {
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
    void pinnedOutCompilation() {
        Long compId = 1L;
        when(compilationService.pinnedOutCompilation(compId)).thenReturn(compilationDto);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/admin/compilations/{compId}/pin", compId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(compilationService, Mockito.times(1))
                .pinnedOutCompilation(compId);

    }
}
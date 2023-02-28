package ru.practicum.main_service.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.service.CategoryService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = PublicCategoryController.class)
class PublicCategoryControllerTest {

    @MockBean
    private CategoryService categoryService;
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private CategoryDto categoryDto1;
    private CategoryDto categoryDto2;

    @Autowired
    public PublicCategoryControllerTest(CategoryService categoryService, MockMvc mockMvc, ObjectMapper mapper) {
        this.categoryService = categoryService;
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @BeforeEach
    void start() {
        categoryDto1 = new CategoryDto(1L, "categoryDto1Name");
        categoryDto2 = new CategoryDto(2L, "categoryDto2Name");
    }

    @Test
    @SneakyThrows
    void getById() {
        Long catId = 1L;
        when(categoryService.getById(anyLong())).thenReturn(categoryDto1);

        mockMvc.perform(get("/categories/{catId}", catId))
                .andDo(print())
                .andExpect(status().isOk());

        verify(categoryService, times(1)).getById(catId);
    }
}
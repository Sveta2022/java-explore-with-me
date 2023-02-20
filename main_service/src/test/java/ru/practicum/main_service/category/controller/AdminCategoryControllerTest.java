package ru.practicum.main_service.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.service.CategoryService;
import org.springframework.http.MediaType;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(controllers = AdminCategoryController.class)
class AdminCategoryControllerTest {
    @MockBean
    private CategoryService categoryService;

    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private CategoryDto categoryDto1;
    private CategoryDto categoryDto2;

    @Autowired
    public AdminCategoryControllerTest(CategoryService categoryService,
                                       MockMvc mockMvc, ObjectMapper mapper) {
        this.categoryService = categoryService;
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @BeforeEach
    void start() {
        categoryDto1 = new CategoryDto(1L, "categoryDto1Name");
        categoryDto2 = new CategoryDto(2L, "categoryDto2Name");
    }

    @SneakyThrows
    @Test
    void createCategory() {
        when(categoryService.create(any())).thenReturn(categoryDto1);

        String result = mockMvc.perform(post("/admin/categories")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(categoryDto1)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(mapper.writeValueAsString(categoryDto1), result);
    }

//    @SneakyThrows
//    @Test
//    void updateCategory() {
//        when(categoryService.update(anyLong(), any())).thenReturn(categoryDto2);
//
//        String result = mockMvc.perform(patch("/admin/categories")
//                        .contentType("application/json")
//                        .content(mapper.writeValueAsString(categoryDto1)))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        assertEquals(mapper.writeValueAsString(categoryDto2), result);
//    }

    @SneakyThrows
    @Test
    void removeCategory() {

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/admin/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(categoryService, Mockito.times(1))
                .delete(1L);
    }
}
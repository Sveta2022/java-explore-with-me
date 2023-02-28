package ru.practicum.main_service.user.controller;

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
import ru.practicum.main_service.user.dto.UserDto;
import ru.practicum.main_service.user.service.UserService;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = AdminUserController.class)
class AdminUserControllerTest {

    @MockBean
    private UserService userService;
    private MockMvc mockMvc;
    private ObjectMapper mapper;
    private UserDto userDto1;
    private UserDto userDto2;

    @Autowired
    public AdminUserControllerTest(UserService userService, MockMvc mockMvc, ObjectMapper mapper) {
        this.userService = userService;
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @BeforeEach
    void start() {
        userDto1 = new UserDto(1L, "user1", "user1@email.ru");
        userDto2 = new UserDto(2L, "user2", "user2@email.ru");
    }

    @SneakyThrows
    @Test
    void create() {
        when(userService.create(any())).thenReturn(userDto1);

        String result = mockMvc.perform(post("/admin/users")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(userDto1)))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(mapper.writeValueAsString(userDto1), result);

    }

    @SneakyThrows
    @Test
    void createUser_whenUserIsNotValid_thenReturnBadRequest() {
        userDto1.setName(null);

        mockMvc.perform(post("/admin/users")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(userDto1)))
                .andExpect(status().isBadRequest());

        Mockito.verify(userService, Mockito.never()).create(userDto1);

    }

    @SneakyThrows
    @Test
    void getAll() {
        when(userService.getAll(List.of(1L, 2L), 1, 10))
                .thenReturn(List.of(userDto1, userDto2));

        mockMvc.perform(get("/admin/users?ids=1,2&from=1&size=10"))
                .andDo(print())
                .andExpect(status().isOk());

        //   perform(get("/admin/users")).andExpect(status().isOk());
        verify(userService, Mockito.times(1))
                .getAll(List.of(1L, 2L), 1, 10);
    }

    @SneakyThrows
    @Test
    void delete() {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/admin/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(userService, Mockito.times(1))
                .delete(1L);
    }
}
package ru.practicum.main_service.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import ru.practicum.main_service.exception.NotFoundObjectException;
import ru.practicum.main_service.user.dao.UserStorage;
import ru.practicum.main_service.user.dto.UserDto;
import ru.practicum.main_service.user.mapper.UserMapper;
import ru.practicum.main_service.user.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserStorage userStorage;

    private User user1;
    private User user2;
    private UserDto userDto1;
    private UserDto userDto2;


    @BeforeEach
    void start() {
        userDto1 = new UserDto(1L, "userDtoname1", "userDto1Email@email");
        userDto2 = new UserDto(2L, "userDtoname2", "userDto2Email@email");

        user1 = UserMapper.toUser(userDto1);
        user2 = UserMapper.toUser(userDto2);

    }

    @Test
    void create() {
        when(userStorage.save(any())).thenReturn(user1);
        UserDto actualUserDto1 = userService.create(userDto1);
        assertEquals(userDto1, actualUserDto1);

    }

    @Test
    void getAll() {
        when(userStorage.findAllByIdIn(List.of(1L, 2L), PageRequest.of(10, 1))).thenReturn(List.of(user1, user2));

        List<UserDto> actualUsersDto = userService.getAll(List.of(user1.getId(), user2.getId()), 10, 1);
        assertEquals(2, actualUsersDto.size());


    }

    @Test
    void getById_whenUserFound_thenReturn() {
        when(userStorage.findById(anyLong())).thenReturn(Optional.of(user1));

        UserDto actualUserDto1 = userService.getById(user1.getId());
        assertEquals(userDto1, actualUserDto1);

        Mockito.verify(userStorage, Mockito.times(1))
                .findById(user1.getId());

    }

    @Test
    void getById_whenUserNotFound_thenReturnException() {
        when(userStorage.findById(anyLong())).thenReturn(Optional.empty());

        NotFoundObjectException notFoundObjectException = assertThrows(NotFoundObjectException.class,
                () -> userService.getById(user1.getId()));

        assertEquals("Пользователя с id " + user1.getId() + " не зарегестрирован",
                notFoundObjectException.getMessage());
    }

    @Test
    void delete() {
    }
}
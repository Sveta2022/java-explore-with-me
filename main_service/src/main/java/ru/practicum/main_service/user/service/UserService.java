package ru.practicum.main_service.user.service;

import ru.practicum.main_service.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);

    //    UserDto update(Long id, UserDto userDto);
//
    UserDto getById(Long id);

    //
    void delete(Long id);

    //
    List<UserDto> getAll(List<Long> ids, int from, int size);
}
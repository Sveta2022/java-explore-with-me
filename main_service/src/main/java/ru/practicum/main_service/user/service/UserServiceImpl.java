package ru.practicum.main_service.user.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main_service.exception.NotFoundObjectException;
import ru.practicum.main_service.user.dao.UserStorage;
import ru.practicum.main_service.user.dto.UserDto;
import ru.practicum.main_service.user.mapper.UserMapper;
import ru.practicum.main_service.user.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserStorage storage;

    @Autowired
    public UserServiceImpl(UserStorage storage) {
        this.storage = storage;
    }

    @Transactional
    @Override
    public UserDto create(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        return UserMapper.toDto(storage.save(user));
    }
    @Override
    public List<UserDto> getAll(List <Long> ids, int from, int size) {
        int page = from / size;
        final Pageable pageable = PageRequest.of(page, size);
        return storage.findAllByIdIn(ids, pageable).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        User user = storage.findById(id)
                .orElseThrow(() -> new NotFoundObjectException("Пользователя с id " + id + " не зарегестрирован"));
        return UserMapper.toDto(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = UserMapper.toUser(getById(id));
        storage.delete(user);
    }
}

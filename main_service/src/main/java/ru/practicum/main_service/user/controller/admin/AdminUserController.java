package ru.practicum.main_service.user.controller.admin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_service.user.dto.UserDto;
import ru.practicum.main_service.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "admin/users")
public class AdminUserController {
    private UserService service;

    @Autowired
    public AdminUserController(UserService service) {
        this.service = service;
    }


    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        log.info("Запрос на добавление пользователя " + userDto.getName() + " id " + userDto.getId() + " создан");
        return service.create(userDto);
    }

    @GetMapping
    public List<UserDto> getAll(@RequestParam(name = "ids", required = false) List<Long> ids,
                                @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") int from,
                                @Positive @RequestParam(name = "size", defaultValue = "10") int size) {
        log.info("Запрос получить всех пользователей создан");
        return service.getAll(ids, from, size);
    }

//    @GetMapping("/{id}")
//    public UserDto getById(@PathVariable Long id) {
//        log.info("Запрос на получение пользователя по id " + id + " создан");
//        return service.getById(id);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Запрос на обновление пользователя с id " + id + " создан");
        service.delete(id);
    }

}

package ru.practicum.main_service.requests.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_service.requests.dto.ParticipationRequestDto;
import ru.practicum.main_service.requests.service.RequestService;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users/{userId}/requests")
public class RequestController {

   private RequestService requestService;

   @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }
    @PostMapping
    public ParticipationRequestDto addRequest(@PathVariable @NotNull Long userId,
                                              @RequestParam Long eventId) {
        log.info("Запрос на участие в событии создан", userId, eventId);
        return requestService.create(userId, eventId);
    }
    @GetMapping
    public List<ParticipationRequestDto> findAllRequests(@PathVariable Long userId) {
        log.info("Получение информации о заявках текущего пользователя с id = {} " +
                "на участие в чужих событиях ", userId);
        return requestService.getAllRequests(userId);
    }

    @PatchMapping("/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable @NotNull Long userId,
                                                 @PathVariable @NotNull Long requestId) {
        log.info("Отмена своего запроса на участие в событии", requestId);
        return requestService.cancelRequest(userId, requestId);
    }

}

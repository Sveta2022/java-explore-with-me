package ru.practicum.main_service.event.controller;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.dto.NewEventDto;
import ru.practicum.main_service.event.service.EventService;
import ru.practicum.main_service.requests.dto.ParticipationRequestDto;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/events")
public class PrivateEventController {
    EventService eventService;

    @Autowired
    public PrivateEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public EventFullDto create(@PathVariable Long userId,
                               @RequestBody @Valid NewEventDto eventNewDto) {
        log.info("Добавление нового события = {}, пользователем с id = {} ", eventNewDto.getEventId(), userId);
        return eventService.create(userId, eventNewDto);
    }

    @GetMapping
    public List<EventShortDto> getAllEventsByUser(@PathVariable Long userId,
                                                  @RequestParam(defaultValue = "0") int from,
                                                  @RequestParam(name = "size", defaultValue = "10") int size) {

        log.info("Получение событий, добавленных текущим пользователем с id = {}", userId);
        return eventService.getAllEventsByUser(userId, from, size);
    }

    @GetMapping("/{eventId}/requests")
    public List<ParticipationRequestDto> getRequestEventByUser(@PathVariable Long userId,
                                                               @PathVariable Long eventId) {
        log.info("Получение информации о запросах на участие в событии id = {} " +
                "текущего пользователя id = {}", eventId, userId);
        return eventService.getRequestEventByUser(userId, eventId);
    }


    @GetMapping("{eventId}")
    public EventFullDto getEventByIdByCreator(@PathVariable Long userId,
                                              @PathVariable Long eventId) {
        log.info("Получение полной информации о событии добавленном текущим пользователем id = {}", eventId);
        return eventService.getEventByIdByCreator(userId, eventId);
    }

    @PatchMapping("{eventId}")
    public EventFullDto cancelByCreator(@PathVariable Long userId,
                                        @PathVariable Long eventId) {
        log.info("Отмена события c id = {}, добавленного текущим пользователем c id = {}", eventId, userId);
        return eventService.cancelByCreator(userId, eventId);

    }


    @PatchMapping
    public EventFullDto updateByCreator(@PathVariable Long userId,
                                        @RequestBody NewEventDto newEventDto) {
        log.info("Изменение события id = {}, добавленного текущим пользователем с id = {} ", newEventDto.getEventId(), userId);
        return eventService.updateByCreator(userId, newEventDto);

    }

    @PatchMapping("/{eventId}/requests/{reqId}/confirm")
    public ParticipationRequestDto confirmRequestForEvent(@PathVariable Long userId,
                                                          @PathVariable Long eventId,
                                                          @PathVariable Long reqId) {
        log.info("Подтверждение чужой заявки на участие в событии текущего пользователя {} {} {}", reqId, userId, eventId);
        return eventService.confirmRequestForEvent(userId, eventId, reqId);

    }

    @PatchMapping("/{eventId}/requests/{reqId}/reject")
    public ParticipationRequestDto rejectRequestForEvent(@PathVariable Long userId,
                                                         @PathVariable Long eventId,
                                                         @PathVariable Long reqId) {
        log.info("Отклонение чужой заявки на участие в событии текущего пользователя {} {}", reqId, userId);
        return eventService.rejectRequestForEvent(userId, eventId, reqId);

    }
}

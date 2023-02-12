package ru.practicum.main_service.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.dto.NewEventDto;
import ru.practicum.main_service.event.service.EventService;

import javax.validation.constraints.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/events")
public class AdminEventController {

    private EventService eventService;

    @Autowired
    public AdminEventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PutMapping("{eventId}")
    public EventFullDto update(@PathVariable @NotNull Long eventId,
                               @RequestBody NewEventDto newEventDto) {
        log.info("Редактирование данных события c id {} администратором. " +
                "Валидация данных не требуется.", eventId);
        return eventService.update(newEventDto, eventId);
    }

    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishByAdmin(@PathVariable @NotNull Long eventId) {
        log.info("Публикация события с id {} ", eventId);
        return eventService.publishByAdmin(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectByAdmin(@PathVariable @NotNull Long eventId) {
        log.info("Отклонение события с id {} ", eventId);
        return eventService.rejectByAdmin(eventId);
    }

    @GetMapping
    public List<EventFullDto> getEventsByParametrs(@RequestParam(required = false) List<Long> users,
                                                   @RequestParam(defaultValue = "PUBLISHED", required = false) List<String> states,
                                                   @RequestParam(required = false) List<Long> categories,
                                                   @RequestParam(required = false) String rangeStart,
                                                   @RequestParam(required = false) String rangeEnd,
                                                   @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {

        log.info("Эндпоинт возвращает полную информацию обо всех событиях подходящих под переданные условия");
        return eventService.getEventsByParametrs(users, states, categories, rangeStart, rangeEnd, from, size);
    }
}
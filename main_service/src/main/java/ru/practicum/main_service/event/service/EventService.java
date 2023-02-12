package ru.practicum.main_service.event.service;


import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.dto.NewEventDto;
import ru.practicum.main_service.event.model.StateEvent;
import ru.practicum.main_service.requests.dto.ParticipationRequestDto;
import ru.practicum.main_service.requests.model.ParticipationRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    EventFullDto create(Long userId, NewEventDto eventNewDto);


    List<EventShortDto> getAllEventsByUser(Long userId, int from, int size);

    EventFullDto update(NewEventDto newEventDto, Long eventId);

    EventFullDto publishByAdmin(Long eventId);

    EventFullDto getEventByIdByCreator(Long userId, Long eventId);

    List<EventShortDto> getEventsWithFilter(String text, List<Long> categoriesId, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, String sort, int from, int size);

    EventFullDto getEventById(Long id);

    EventFullDto updateByCreator(Long userId, NewEventDto newEventDto);

    List<EventFullDto> getEventsByParametrs(List<Long> users, List<String> states, List<Long> categories, String rangeStart, String rangeEnd, Integer from, Integer size);

    EventFullDto rejectByAdmin(Long eventId);

    List<ParticipationRequestDto> getRequestEventByUser(Long userId, Long eventId);

    ParticipationRequestDto confirmRequestForEvent(Long userId, Long eventId, Long reqId);

    ParticipationRequestDto rejectRequestForEvent(Long userId, Long eventId, Long reqId);

    EventFullDto cancelByCreator(Long userId, Long eventId);
}

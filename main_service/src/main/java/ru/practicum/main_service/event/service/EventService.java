package ru.practicum.main_service.event.service;


import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.dto.EventUpdateRequestDto;
import ru.practicum.main_service.event.dto.NewEventDto;
import ru.practicum.main_service.requests.dto.EventRequestStatusUpdateResult;
import ru.practicum.main_service.requests.dto.ParticipationRequestDto;
import ru.practicum.main_service.requests.dto.ParticipationRequestStatusUpdate;

import java.util.List;

public interface EventService {
    EventFullDto create(Long userId, NewEventDto eventNewDto);


    List<EventShortDto> getAllEventsByUser(Long userId, int from, int size);

    EventFullDto update(NewEventDto newEventDto, Long eventId);

    EventFullDto getEventByIdByCreator(Long userId, Long eventId);

    List<EventShortDto> getEventsWithFilter(String text, List<Long> categoriesId, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, String sort, int from, int size);

    EventFullDto getEventById(Long id);

    EventFullDto updateByCreator(Long userId, Long eventId, EventUpdateRequestDto eventUpdateRequestDto);

    List<EventFullDto> getEventsByParametrs(List<Long> users, List<String> states, List<Long> categories, String rangeStart, String rangeEnd, Integer from, Integer size);

    List<ParticipationRequestDto> getRequestEventByUser(Long userId, Long eventId);

    ParticipationRequestDto confirmRequestForEvent(Long userId, Long eventId, Long reqId);

    EventRequestStatusUpdateResult updateRequestStatusForEvent(Long userId, Long eventId, ParticipationRequestStatusUpdate participationRequestDto);

    EventFullDto updateEventByAdmin(Long eventId, EventUpdateRequestDto eventUpdateRequestDto);
}

package ru.practicum.main_service.requests.service;

import ru.practicum.main_service.requests.dto.ParticipationRequestDto;

import java.util.List;

public interface RequestService {
    ParticipationRequestDto create(Long userId, Long eventId);

    List<ParticipationRequestDto> getAllRequests(Long userId);

    ParticipationRequestDto cancelRequest(Long userId, Long requestId);
}

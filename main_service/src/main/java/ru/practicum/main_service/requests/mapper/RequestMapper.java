package ru.practicum.main_service.requests.mapper;

import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.requests.dto.ParticipationRequestDto;
import ru.practicum.main_service.requests.model.ParticipationRequest;
import ru.practicum.main_service.user.model.User;

import java.time.LocalDateTime;

import static ru.practicum.main_service.event.Formatter.FORMATTER;

public class RequestMapper {

    public static ParticipationRequestDto toRequestEventDto(ParticipationRequest participationRequest) {
        return ParticipationRequestDto.builder()
                .id(participationRequest.getId())
                .requester(participationRequest.getRequester().getId())
                .event(participationRequest.getEvent().getId())
                .status(participationRequest.getStatus())
                .created(participationRequest.getCreated().format(FORMATTER))
                .build();
    }

    public static ParticipationRequest toRequestEvent(ParticipationRequestDto requestEventDto, User user, Event event) {
        return ParticipationRequest.builder()
                .id(requestEventDto.getId())
                .created(LocalDateTime.parse(requestEventDto.getCreated(), FORMATTER))
                .requester(user)
                .event(event)
                .status(requestEventDto.getStatus())
                .build();
    }
}

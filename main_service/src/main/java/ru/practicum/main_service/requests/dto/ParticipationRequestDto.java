package ru.practicum.main_service.requests.dto;

import lombok.*;
import ru.practicum.main_service.requests.model.RequestEventStatus;


@Getter
@Setter
@Builder
public class ParticipationRequestDto {
    private Long id;
    private Long event;
    private Long requester;
    private String created;
    private RequestEventStatus status;

}

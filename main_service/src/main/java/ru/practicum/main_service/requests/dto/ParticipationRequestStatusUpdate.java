package ru.practicum.main_service.requests.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.main_service.requests.model.RequestEventStatus;

import java.util.List;

@Getter
@Setter
@Builder
public class ParticipationRequestStatusUpdate {
    private List<Long> requestIds;  // Идентификаторы запросов на участие в событии текущего пользователя
    private RequestEventStatus status;  //Новый статус запроса на участие в событии текущего пользователя

}

package ru.practicum.main_service.event.dto.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.main_service.event.dto.StateAction;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class EventUpdateRequestDto {

    private Long eventId;

    @NotBlank
    @Size(min = 20, max = 2000)
    private String annotation; // Новая аннотация

    @NotNull
    @Positive
    private Long category; //Новая категория

    @NotBlank
    @Size(min = 20, max = 7000)
    private String description; //Новое описание

    @NotNull
    private String eventDate; // Новые дата и время на которые намечено событие.

    @NotNull
    private NewEventDto.LocationDto location;  // Широта и долгота места проведения события

    @NotNull
    private Boolean paid;  // Новое значение флага о платности мероприятия

    @PositiveOrZero
    private Integer participantLimit; // Новый лимит пользователей

    @NotNull
    private Boolean requestModeration;  // Нужна ли пре-модерация заявок на участие

    @NotNull
    @Size(min = 3, max = 120)
    private String title;  //Новый заголовок

    StateAction stateAction;

    @Getter
    @Setter
    @Builder
    public static class LocationDto {
        private Double lat;
        private Double lon;
    }
}

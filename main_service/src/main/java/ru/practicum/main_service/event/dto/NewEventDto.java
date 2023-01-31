package ru.practicum.main_service.event.dto;


import lombok.*;

import javax.validation.constraints.*;


@Getter
@Setter
@Builder

public class NewEventDto {

    private Long eventId;

    @NotBlank
    @Size(min = 20, max = 2000)
    private String annotation;

    @NotNull
    @Positive
    private Long category;

    @NotBlank
    @Size(min = 20, max = 7000)
    private String description;

    @NotNull
    private String eventDate;

    @NotNull
    private LocationDto location;

    @NotNull
    private Boolean paid;
    @PositiveOrZero
    private Integer participantLimit;

    @NotNull
    private Boolean requestModeration;
    @NotNull
    @Size(min = 3, max = 120)
    private String title;


    @Getter
    @Setter
    @Builder
    public static class LocationDto {
        private Double lat;
        private Double lon;
    }
}
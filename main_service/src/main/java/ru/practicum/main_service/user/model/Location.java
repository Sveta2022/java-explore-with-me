package ru.practicum.main_service.user.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
@Embeddable
public class Location {
    private double lat;
    private double lon;

}

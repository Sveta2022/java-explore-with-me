package ru.practicum.main_service.user.model;

import lombok.*;

import javax.persistence.Embeddable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Embeddable
public class Location {
    private double lat;
    private double lon;

}

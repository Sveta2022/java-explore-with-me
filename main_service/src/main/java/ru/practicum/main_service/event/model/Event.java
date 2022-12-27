package ru.practicum.main_service.event.model;


import lombok.*;
import ru.practicum.main_service.category.model.CategoryEvent;
import ru.practicum.main_service.user.model.Location;
import ru.practicum.main_service.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "annotation")
    private String annotation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEvent category;

    @Column(name = "description")
    private String description;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "lat", column = @Column(name = "location_lat")),
            @AttributeOverride( name = "lon", column = @Column(name = "location_lon"))
    })
    private Location location;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "participantLimit")
    private int participantLimit;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @Column(name = "request_moderation")
    private boolean requestModeration;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private StateEvent stateEvent;

    @Column(name = "view")
    private Long views;


}

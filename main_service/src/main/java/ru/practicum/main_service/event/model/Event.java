package ru.practicum.main_service.event.model;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String title;  // Заголовок

    @Column(name = "annotation")
    private String annotation;  //Краткое описание

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEvent category;  //Категория

    @Column(name = "description")
    private String description;  //Полное описание события

    @Column(name = "event_date")
    private LocalDateTime eventDate; // Дата и время на которые намечено событие (в формате "yyyy-MM-dd HH:mm:ss")

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lat", column = @Column(name = "location_lat")),
            @AttributeOverride(name = "lon", column = @Column(name = "location_lon"))
    })
    private Location location;

    @Column(name = "paid")
    private boolean paid;  //Нужно ли оплачивать участие

    @Column(name = "participant_limit")
    private int participantLimit;  //Ограничение на количество участников. Значение 0 - означает отсутствие ограничения

    @Column(name = "created_on")
    private LocalDateTime createdOn;  //Дата и время создания события (в формате "yyyy-MM-dd HH:mm:ss")

    @ManyToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;  //тот, кто создал событие

    @Column(name = "published_on")
    private LocalDateTime publishedOn;  //Дата и время публикации события (в формате "yyyy-MM-dd HH:mm:ss")

    @Column(name = "request_moderation")
    private boolean requestModeration; //ужна ли пре-модерация заявок на участие

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private StateEvent stateEvent;  //Список состояний жизненного цикла события

    @Column(name = "views")
    private Long views = 0L;  //Количество просмотрев события


}

package ru.practicum.main_service.compilation.model;

import lombok.*;
import ru.practicum.main_service.event.model.Event;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "compilations")
public class Compilation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  //Идентификатор

    @NotNull
    @Column(name = "pinned")
    private Boolean pinned;  //Закреплена ли подборка на главной странице сайта

    @NotBlank
    @Column(name = "title")
    @Size(min = 3, max = 120)
    private String title;   //Заголовок подборки

    @ManyToMany
    @JoinTable(name = "compilation_event",
            joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events;   //Список событий входящих в подборку
}


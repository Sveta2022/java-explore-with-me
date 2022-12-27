package ru.practicum.main_service.category.model;

import lombok.*;
import ru.practicum.main_service.event.model.Event;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "categorys")
public class CategoryEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Event> events;

}

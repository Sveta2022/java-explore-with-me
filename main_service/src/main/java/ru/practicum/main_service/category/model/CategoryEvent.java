package ru.practicum.main_service.category.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "categorys")
public class CategoryEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 1, max = 255)
    private String name;
}

package ru.practicum.main_service.user.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(min = 1, max = 255)
    private String name;

    @Column(name = "email", unique = true)
    private String email;
}

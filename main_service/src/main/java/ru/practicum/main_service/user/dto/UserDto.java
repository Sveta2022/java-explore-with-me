package ru.practicum.main_service.user.dto;

import lombok.*;
import ru.practicum.main_service.exception.Create;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "id")
public class UserDto {

    @Positive(groups = Create.class)
    private Long id;
    @NotBlank(groups = Create.class)
    private String name;
    @Email(groups = Create.class)
    @NotBlank(groups = Create.class)
    private String email;
}

package ru.practicum.main_service.event.dto.comment;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentDto {

    @NotNull
    @Size(min = 3, max = 200)
    private String text;

}
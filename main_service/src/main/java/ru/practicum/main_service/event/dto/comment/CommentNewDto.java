package ru.practicum.main_service.event.dto.comment;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentNewDto {

    private Long id;
    private String text;
}

package ru.practicum.main_service.event.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentFullDto {

    private Long id;
    private String text;
    private UserShortDto author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;


    @Getter
    @Setter
    @Builder
    public static class UserShortDto {
        private Long id;
        private String name;
        private String email;
    }
}

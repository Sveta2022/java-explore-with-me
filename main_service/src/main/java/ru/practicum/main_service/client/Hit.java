package ru.practicum.main_service.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    @EqualsAndHashCode(of = "id")
    public class Hit {
        @NotBlank
        private String app;
        @NotBlank
        private String uri;
        @NotBlank
        private String ip;
        private String timestamp;
    }


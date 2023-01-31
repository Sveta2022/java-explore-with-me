package ru.practicum.main_service.compilation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
public class CompilationDto {

    private Long id;
    private Boolean pinned;
    private String title;
    @Builder.Default
    private Set<EventShortDto> events = new HashSet<>();
    @Getter
    @Setter
    @Builder
    public static class EventShortDto {

        private String annotation;
        private CategoryDto category;
        private Integer confirmedRequests;
        private String eventDate;
        private Long id;
        private UserShortDto initiator;
        private Boolean paid;
        private String title;

        @Getter
        @Setter
        @Builder
        public static class UserShortDto {
            private Long id;
            private String name;
        }

        @Getter
        @Setter
        @Builder
        public static class CategoryDto {
            private Long id;
            private String name;
        }
    }
}

package ru.practicum.main_service.compilation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Builder
public class NewCompilationDto {
    private Boolean pinned;
    @NotBlank
    private String title;
    private Set<Long> events;
}

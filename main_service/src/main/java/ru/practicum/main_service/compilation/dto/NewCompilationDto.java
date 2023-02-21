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

    private Boolean pinned; //Закреплена ли подборка на главной странице сайта
    @NotBlank
    private String title;  //Заголовок подборки
    private Set<Long> events;  //Список id событий подборки для полной замены текущего списка

}

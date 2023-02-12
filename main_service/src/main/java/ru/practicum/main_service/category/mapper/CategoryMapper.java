package ru.practicum.main_service.category.mapper;

import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.model.CategoryEvent;


public class CategoryMapper {

    public static CategoryEvent toCategory(CategoryDto categoryDto) {
        return CategoryEvent.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }

    public static CategoryDto toCategoryDto(CategoryEvent categoryEvent) {
        return CategoryDto.builder()
                .id(categoryEvent.getId())
                .name(categoryEvent.getName())
                .build();
    }

}

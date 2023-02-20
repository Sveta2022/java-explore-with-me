package ru.practicum.main_service.category.service;

import ru.practicum.main_service.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {


    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(Long catId, CategoryDto categoryDto);

    void delete(Long catId);

    CategoryDto getById(Long catId);

    List<CategoryDto> getAll(Integer from, Integer size);
}

package ru.practicum.main_service.category.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.service.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "admin/categories")
public class AdminCategoryController {

    private CategoryService categoryService;

    @Autowired
    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("Запрос на добавление категории" + categoryDto.getName());
        return categoryService.create(categoryDto);
    }

    @PatchMapping
    public CategoryDto updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("Запрос на изменение категории" + categoryDto.getName());
        return categoryService.update(categoryDto);
    }

    @DeleteMapping(value = "/{catId}")
    public void removeCategory(@PathVariable @NotNull Long catId) {
        log.info("Запрос на удаление категории" + catId);
        categoryService.delete(catId);
    }

}

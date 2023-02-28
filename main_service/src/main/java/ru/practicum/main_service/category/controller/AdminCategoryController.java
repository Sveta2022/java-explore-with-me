package ru.practicum.main_service.category.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("Добавление новой категории с name = {}", categoryDto.getName());
        return categoryService.create(categoryDto);
    }

    @PatchMapping("/{catId}")
    public CategoryDto updateCategory(@PathVariable @NotNull Long catId,
                                      @Valid @RequestBody CategoryDto categoryDto) {
        log.info("Изменение категории с name = {}", catId);
        return categoryService.update(catId, categoryDto);
    }

    @DeleteMapping(value = "/{catId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCategory(@PathVariable @NotNull Long catId) {
        log.info("Удаление категории с id = {}", catId);
        categoryService.delete(catId);
    }
}

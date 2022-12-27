package ru.practicum.main_service.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_service.category.dto.CategoryDto;
import ru.practicum.main_service.category.service.*;

import javax.validation.constraints.*;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/categories")
public class PublicCategoryController {

    private CategoryService categoryService;

    @Autowired
    public PublicCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{catId}")
    public CategoryDto getById(@PathVariable Long catId) {
        log.info("Запрос на получить категории по id " + catId);
        return categoryService.getById(catId);
    }

    @GetMapping
    public List<CategoryDto> getAll(@PositiveOrZero @RequestParam(name = "from", defaultValue = "0")
                                    Integer from,
                                    @Positive @RequestParam(name = "size", defaultValue = "10")
                                    Integer size) {
        log.info("Запрос получить все категории");
        return categoryService.getAll(from, size);
    }


}

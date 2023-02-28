package ru.practicum.main_service.compilation.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.main_service.compilation.dto.CompilationDto;
import ru.practicum.main_service.compilation.dto.NewCompilationDto;
import ru.practicum.main_service.compilation.seervice.CompilationService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Slf4j
@Validated
@RestController
@RequestMapping("/admin/compilations")
public class AdminCompilationController {

    private final CompilationService compilationService;

    @Autowired
    public AdminCompilationController(CompilationService compilationService) {
        this.compilationService = compilationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto creat(@Valid @RequestBody NewCompilationDto newCompilationDto) {

        log.info("Добавление новой подборки");
        return compilationService.create(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCompilationById(@PathVariable @NotNull Long compId) {

        log.info("Удаление подборки с id = {}", compId);
        compilationService.removeCompilationById(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto updateCompilation(@PathVariable Long compId,
                                            @RequestBody NewCompilationDto newCompilationDto
    ) {
        log.info("Обновить информацию о подборке с id = {}", compId);
        return compilationService.updateCompilation(compId, newCompilationDto);
    }
}

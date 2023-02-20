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

    @DeleteMapping("/{compId}/events/{eventId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeEventFromCompilation(@PathVariable @NotNull Long compId,
                                           @PathVariable @NotNull Long eventId) {

        log.info("Удалить событие по id = {} из подборки c id = {}", eventId, compId);
        compilationService.removeEventFromCompilation(compId, eventId);
    }

    @PatchMapping("/{compId}/events/{eventId}")
    public CompilationDto addEventInCompilation(@PathVariable @NotNull Long compId,
                                                @PathVariable @NotNull Long eventId) {

        log.info("Добавить событие по id = {} в подборку c id = {}", eventId, compId);
        return compilationService.addEventToCompilation(compId, eventId);
    }

    @DeleteMapping("/{compId}/pin")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public CompilationDto pinnedOutCompilation(@PathVariable @NotNull Long compId) {

        log.info("Открепить подборку на главной странице с id = {}", compId);
        return compilationService.pinnedOutCompilation(compId);
    }

    @PatchMapping("/{compId}/pin")
    public CompilationDto pinnedCompilation(@PathVariable @NotNull Long compId) {

        log.info("Закрепить подборку на главной странице с id = {}", compId);
        return compilationService.pinnedCompilation(compId);
    }
}

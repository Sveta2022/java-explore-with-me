package ru.practicum.ewm_stats.controller;

import ru.practicum.ewm_stats.dto.EndpointHitDto;
import ru.practicum.ewm_stats.model.ViewStats;
import ru.practicum.ewm_stats.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StatsController {

    private StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @PostMapping("/hit")
    public void create(@RequestBody EndpointHitDto endpointHitDto) {
        log.info("Сохранение информации о том, что к эндпоинту был запрос по uri {}", endpointHitDto.getUri());
        statsService.create(endpointHitDto);
    }

    @GetMapping("/stats")
    public List<ViewStats> getViewStats(@RequestParam(name = "start") String start,
                                        @RequestParam(name = "end") String end,
                                        @RequestParam(name = "uris", required = false) List<String> uris,
                                        @RequestParam(name = "unique", defaultValue = "false") boolean unique) {
        log.info("Статистика по посещению сайта");
        return statsService.getViewStats(start, end, uris, unique);
    }


}

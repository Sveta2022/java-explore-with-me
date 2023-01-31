package com.example.statsservice.stat.controller;


import com.example.statsservice.stat.dto.EndpointHitDto;
import com.example.statsservice.stat.model.ViewStats;
import com.example.statsservice.stat.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public void create(@Valid @RequestBody EndpointHitDto endpointHitDto){
        log.info("Ссылка для uri сохранена {}", endpointHitDto.getUri());
        statsService.create(endpointHitDto);
    }

    @GetMapping("/hit")
    public List<ViewStats> getViewStats(@RequestParam(name = "start") String start,
                                        @RequestParam(name = "end") String end,
                                        @RequestParam(name = "uris", required = false) List<String> uris,
                                        @RequestParam(name = "unique", defaultValue = "false") boolean unique){
        log.info("Статистика по посещению сайта");
        return statsService.getViewStats(start, end, uris, unique);
    }


}

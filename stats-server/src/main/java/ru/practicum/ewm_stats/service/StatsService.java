package ru.practicum.ewm_stats.service;

import ru.practicum.ewm_stats.dto.EndpointHitDto;
import ru.practicum.ewm_stats.model.EndpointHit;
import ru.practicum.ewm_stats.model.ViewStats;

import java.util.List;

public interface StatsService {
    void create(EndpointHitDto endpointHitDto);

    List<ViewStats> getViewStats(String start, String end, List<String> uris, boolean unique);
}

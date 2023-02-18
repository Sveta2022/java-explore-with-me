package ru.practicum.service;

import ru.practicum.dto.EndpointHitDto;
import ru.practicum.model.ViewStats;

import java.util.List;

public interface StatsService {

    void create(EndpointHitDto endpointHitDto);

    List<ViewStats> getViewStats(String start, String end, List<String> uris, boolean unique);
}

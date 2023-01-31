package com.example.statsservice.stat.service;

import com.example.statsservice.stat.dao.StatsStorage;
import com.example.statsservice.stat.dto.EndpointHitDto;
import com.example.statsservice.stat.model.ViewStats;

import java.util.List;

public interface StatsService {
    public void create(EndpointHitDto endpointHitDto);

    List<ViewStats> getViewStats(String start, String end, List<String> uris, boolean unique);
}

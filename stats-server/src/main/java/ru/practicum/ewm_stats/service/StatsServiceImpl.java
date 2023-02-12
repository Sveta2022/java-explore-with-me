package ru.practicum.ewm_stats.service;

import ru.practicum.ewm_stats.dao.StatsStorage;
import ru.practicum.ewm_stats.dto.EndpointHitDto;
import ru.practicum.ewm_stats.mapper.StatsMapper;
import ru.practicum.ewm_stats.model.EndpointHit;
import ru.practicum.ewm_stats.model.ViewStats;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@Transactional(readOnly = true)
public class StatsServiceImpl implements StatsService {

    private StatsStorage statStorage;

    @Autowired
    public StatsServiceImpl(StatsStorage statStorage) {
        this.statStorage = statStorage;
    }

    @Override
    @Transactional
    public void create(EndpointHitDto endpointHitDto){

        statStorage.save(StatsMapper.toEndpointHit(endpointHitDto));
    }

    @Override
    public List<ViewStats> getViewStats(String start, String end, List<String> uris, boolean unique) {
        LocalDateTime startOfTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endOfTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (unique) {
            return statStorage.findAllUnique(startOfTime, endOfTime, uris, unique);
        } else {
            return statStorage.findAll(startOfTime, endOfTime, uris);
        }
    }
}

package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dao.StatsStorage;
import ru.practicum.dto.EndpointHitDto;
import ru.practicum.mapper.StatsMapper;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


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
    public void create(EndpointHitDto endpointHitDto) {

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

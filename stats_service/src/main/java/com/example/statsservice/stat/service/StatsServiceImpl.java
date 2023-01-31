package com.example.statsservice.stat.service;

import com.example.statsservice.stat.dao.StatsStorage;
import com.example.statsservice.stat.dto.EndpointHitDto;
import com.example.statsservice.stat.mapper.StatsMapper;
import com.example.statsservice.stat.model.EndpointHit;
import com.example.statsservice.stat.model.ViewStats;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void create(EndpointHitDto endpointHitDto){
        EndpointHit endpointHit = StatsMapper.toEndpointHit(endpointHitDto);
        statStorage.save(endpointHit);
    }

    @Override
    public List<ViewStats> getViewStats(String start, String end, List<String> uris, boolean unique) {
        LocalDateTime startOfTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endOfTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (unique) {
            return statStorage.findAllByUnique(startOfTime, endOfTime, uris, unique);
        } else {
            return statStorage.findAll(startOfTime, endOfTime, uris);
        }
    }
}

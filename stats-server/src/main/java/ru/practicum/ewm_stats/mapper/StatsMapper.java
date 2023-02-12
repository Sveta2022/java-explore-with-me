package ru.practicum.ewm_stats.mapper;

import ru.practicum.ewm_stats.dto.EndpointHitDto;
import ru.practicum.ewm_stats.model.EndpointHit;
import ru.practicum.ewm_stats.model.ViewStats;

import java.time.LocalDateTime;

public class StatsMapper {

    public static EndpointHit toEndpointHit(EndpointHitDto endpointHitDto){
        return EndpointHit.builder()
                .uri(endpointHitDto.getUri())
                .app(endpointHitDto.getApp())
                .ip(endpointHitDto.getIp())
                .timestamp(LocalDateTime.parse(endpointHitDto.getTimestamp(), Formatter.FORMATTER))
                .build();

    }

    public static EndpointHitDto toEndpointHitDto(EndpointHit endpointHit){
        return EndpointHitDto.builder()
                .app(endpointHit.getApp())
                .uri(endpointHit.getUri())
                .ip(endpointHit.getIp())
                .timestamp(endpointHit.getTimestamp().format(Formatter.FORMATTER))
                .build();
    }

    public static ViewStats toViewStats(EndpointHitDto endpointHitDto) {
        return ViewStats
                .builder()
                .app(endpointHitDto.getApp())
                .uri(endpointHitDto.getUri())
                .build();
    }
}

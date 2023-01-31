package com.example.statsservice.stat.mapper;

import com.example.statsservice.stat.dto.EndpointHitDto;
import com.example.statsservice.stat.model.EndpointHit;
import com.example.statsservice.stat.model.ViewStats;

public class StatsMapper {

    public static EndpointHit toEndpointHit(EndpointHitDto endpointHitDto){
        return EndpointHit.builder()
                .uri(endpointHitDto.getUri())
                .app(endpointHitDto.getApp())
                .ip(endpointHitDto.getIp())
                .timestamp(endpointHitDto.getTimestamp())
                .build();

    }

    public static EndpointHitDto toEndpointHitDto(EndpointHit endpointHit){
        return EndpointHitDto.builder()
                .app(endpointHit.getApp())
                .uri(endpointHit.getUri())
                .ip(endpointHit.getIp())
                .timestamp(endpointHit.getTimestamp())
                .build();

    }
}

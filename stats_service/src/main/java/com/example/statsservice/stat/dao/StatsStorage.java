package com.example.statsservice.stat.dao;

import com.example.statsservice.stat.model.EndpointHit;
import com.example.statsservice.stat.model.ViewStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsStorage extends JpaRepository<EndpointHit, Long> {
    @Query("select new com.example.statsservice.stat.model.ViewStats(e.app, e.uri, count (e.ip)) from  EndpointHit e where e.timestamp > ?1 and " +
            "e.timestamp < ?2 group by e.app, e.uri")
    List<ViewStats> findAll(LocalDateTime startOfTime, LocalDateTime endOfTime, List<String> uris);

    @Query("select new com.example.statsservice.stat.model.ViewStats(e.app, e.uri, count (distinct e.ip)) from  EndpointHit e where e.timestamp > ?1 and " +
            "e.timestamp < ?2 group by e.app, e.uri")
    List<ViewStats> findAllByUnique(LocalDateTime startOfTime, LocalDateTime endOfTime, List<String> uris, boolean unique);
}

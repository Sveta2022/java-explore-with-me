package ru.practicum.ewm_stats.dao;

import org.springframework.data.jpa.repository.Query;
import ru.practicum.ewm_stats.model.EndpointHit;
import ru.practicum.ewm_stats.model.ViewStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StatsStorage extends JpaRepository<EndpointHit, Long> {

    @Query("SELECT new ru.practicum.ewm_stats.model.ViewStats(e.app, e.uri, COUNT (e.ip)) " +
            "from EndpointHit e WHERE e.timestamp> ?1 AND e.timestamp< ?2 and e.uri in ?3 GROUP BY e.app, e.uri ORDER BY COUNT (e.ip) DESC")
    List<ViewStats> findAll(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT new ru.practicum.ewm_stats.model.ViewStats(e.app, e.uri, COUNT (DISTINCT e.ip)) from " +
            "EndpointHit e WHERE e.timestamp> ?1 AND e.timestamp< ?2 and e.uri in ?3 GROUP BY e.app, e.uri ORDER BY COUNT (e.ip) DESC")
    List<ViewStats> findAllUnique(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique);

}

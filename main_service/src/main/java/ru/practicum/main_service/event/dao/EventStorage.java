package ru.practicum.main_service.event.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.main_service.event.model.Event;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface EventStorage extends JpaRepository<Event, Long> {

    List<Event> findAllByInitiatorId(Long userId, Pageable pageable);

    Event findByIdAndInitiatorId(Long eventId, Long userId);

    Optional<Event> findByCategoryId(Long catId);

    @Query(nativeQuery = true,
            value = " select * from events as e " +
                    "where (lower(e.annotation) like lower(concat('%', ?1, '%')) " +
                    "or lower(e.description) like lower(concat('%', ?1, '%'))) " +
                    "and e.category_id in ?2 " +
                    "and e.paid = ?3 " +
                    "and e.state = ?4 ")
    Page<Event> findEvents(String text, List<Long> categoriesId, Boolean paid, String state, Pageable pageable);

    @Query(nativeQuery = true,
            value = "select * from events as e " +
                    "where (?1 is null or e.initiator_id in ?1) " +
                    "and (?2 is null or e.state in ?2) " +
                    "and (?3 is null or e.category_id in ?3) " +
                    "and (e.event_date >= ?4) " +
                    "and (e.event_date <= ?5) ")
    List<Event> findEventsByAdmin(List<Long> users, List<String> stateValues,
                                  List<Long> categories, LocalDateTime rangeStart,
                                  LocalDateTime rangeEnd, Pageable pageable);
}

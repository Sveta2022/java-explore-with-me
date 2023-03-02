package ru.practicum.main_service.event.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.main_service.event.model.Comment;

import java.util.List;

public interface CommentStorage extends JpaRepository<Comment, Long> {
    List<Comment> findAllByEventId(Long eventId);

    Comment findByText(String text);
}

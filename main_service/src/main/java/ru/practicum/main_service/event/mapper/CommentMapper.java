package ru.practicum.main_service.event.mapper;

import ru.practicum.main_service.event.dto.comment.CommentDto;
import ru.practicum.main_service.event.dto.comment.CommentFullDto;
import ru.practicum.main_service.event.model.Comment;

public class CommentMapper {

    public static Comment toComment(CommentDto commentDto) {
        return Comment.builder()
                .text(commentDto.getText())
                .build();
    }

    public static CommentDto commentDto(Comment comment) {
        return CommentDto.builder()
                .text(comment.getText())
                .build();
    }

    public static CommentFullDto commentFullDto(Comment comment) {
        return CommentFullDto.builder()
                .id(comment.getId())
                .author(CommentFullDto.UserShortDto.builder()
                        .id(comment.getAuthor().getId())
                        .name(comment.getAuthor().getName())
                        .email(comment.getAuthor().getEmail())
                        .build())
                .text(comment.getText())
                .created(comment.getCreated())
                .build();
    }

}


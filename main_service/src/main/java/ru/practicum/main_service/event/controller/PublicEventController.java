package ru.practicum.main_service.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.client.EventClient;
import ru.practicum.main_service.event.dto.comment.CommentDto;
import ru.practicum.main_service.event.dto.comment.CommentFullDto;
import ru.practicum.main_service.event.dto.comment.CommentNewDto;
import ru.practicum.main_service.event.dto.event.EventFullDto;
import ru.practicum.main_service.event.dto.event.EventShortDto;
import ru.practicum.main_service.event.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class PublicEventController {

    private final EventService eventService;
    private final EventClient eventClient;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(name = "text", required = false) String text, //текст для поиска в содержимом аннотации и подробном описании события
                                         @RequestParam(name = "categories", required = false) List<Long> categoriesId, //список идентификаторов категорий в которых будет вестись поиск
                                         @RequestParam(name = "paid", required = false) Boolean paid, //поиск только платных/бесплатных событий
                                         @RequestParam(name = "rangeStart", required = false) String rangeStart, //дата и время не раньше которых должно произойти событие
                                         @RequestParam(name = "rangeEnd", required = false) String rangeEnd,  //дата и время не позже которых должно произойти событие
                                         @RequestParam(name = "onlyAvailable", defaultValue = "false") Boolean onlyAvailable, //только события у которых не исчерпан лимит запросов на участие
                                         @RequestParam(name = "sort", required = false) String sort,  //Вариант сортировки: по дате события или по количеству просмотров
                                         @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") int from,  //количество событий, которые нужно пропустить для формирования текущего набора
                                         @Positive @RequestParam(name = "size", defaultValue = "10") int size,  //количество событий в наборе
                                         HttpServletRequest httpServletRequest) {
        log.info("Получение событий с возможностью фильтрации: text = {}, categoriesId = {}, paid = {}, rangeStart = {}, rangeEnd = {}, " +
                        "onlyAvailable = {}, sort = {}, from = {}, size = {}", text, categoriesId, paid, rangeStart, rangeEnd,
                onlyAvailable, sort, from, size);
        eventClient.createEndPointHit(httpServletRequest);
        return eventService.getEventsWithFilter(text, categoriesId, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping("/{id}")
    public EventFullDto getEventById(@PathVariable Long id,
                                     HttpServletRequest httpServletRequest) {
        log.info("Получение подробной информации об опубликованном событии по id = {}", id);
        eventClient.createEndPointHit(httpServletRequest);
        return eventService.getEventById(id);
    }

    @PostMapping("/{eventId}/comment/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentFullDto addComment(@PathVariable Long eventId,
                                     @PathVariable Long userId,
                                     @RequestBody CommentDto commentDto) {
        log.info("Добавление комментария к событию по id = {} пользователем с id = {}", eventId, userId);
        return eventService.addComment(eventId, userId, commentDto);
    }

    @PatchMapping("/{eventId}/comment/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentFullDto updateComment(@PathVariable Long eventId,
                                        @PathVariable Long userId,
                                        @RequestBody CommentNewDto commentUpdate) {
        log.info("Обновление комментария к событию по id = {} пользователем с id = {}", eventId, userId);
        return eventService.updateComment(eventId, userId, commentUpdate);
    }

    @DeleteMapping("/{eventId}/comment/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long eventId,
                                 @PathVariable Long userId,
                                 @RequestBody CommentNewDto commentDelete) {
        log.info("Обновление комментария к событию по id = {} пользователем с id = {}", eventId, userId);
        eventService.deleteComment(eventId, userId, commentDelete);
    }
}


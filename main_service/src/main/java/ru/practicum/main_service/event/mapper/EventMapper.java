package ru.practicum.main_service.event.mapper;

import ru.practicum.main_service.category.model.CategoryEvent;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.dto.EventShortDto;
import ru.practicum.main_service.event.dto.EventUpdateRequestDto;
import ru.practicum.main_service.event.dto.NewEventDto;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.model.StateEvent;
import ru.practicum.main_service.user.model.Location;

import java.time.LocalDateTime;

import static ru.practicum.main_service.event.Formatter.FORMATTER;


public class EventMapper {

    public static EventShortDto eventShortDto(Event event, int confirmedRequests) {
        return EventShortDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(EventShortDto.CategoryDto.builder()
                        .id(event.getCategory().getId())
                        .name(event.getCategory().getName())
                        .build())
                .confirmedRequests(confirmedRequests)
                .eventDate(event.getEventDate().format(FORMATTER))
                .initiator(EventShortDto.UserShortDto.builder()
                        .id(event.getInitiator().getId())
                        .name(event.getInitiator().getName())
                        .build())
                .paid(event.isPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    public static Event toEvent(NewEventDto newEventDto) {
        return Event.builder()
                .annotation(newEventDto.getAnnotation())
                .category(CategoryEvent.builder()
                        .id(newEventDto.getCategory())
                        .name(null)
                        .build())
                .description(newEventDto.getDescription())
                .eventDate(LocalDateTime.parse(newEventDto.getEventDate(), FORMATTER))
                .location(Location.builder()
                        .lat(newEventDto.getLocation().getLat())
                        .lon(newEventDto.getLocation().getLon())
                        .build())
                .paid(newEventDto.getPaid())
                .participantLimit(newEventDto.getParticipantLimit())
                .requestModeration(newEventDto.getRequestModeration())
                .title(newEventDto.getTitle())
                .stateEvent(StateEvent.PENDING)
                .createdOn(LocalDateTime.now())
                .build();
    }

    public static EventFullDto eventFullDto(Event event, int confirmedRequests) {
        return EventFullDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(EventFullDto.CategoryDto.builder()
                        .id(event.getCategory().getId())
                        .name(event.getCategory().getName())
                        .build())
                .confirmedRequests(0)
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .initiator(EventFullDto.UserShortDto.builder()
                        .id(event.getInitiator().getId())
                        .name(event.getInitiator().getName())
                        .build())
                .location(EventFullDto.LocationDto
                        .builder()
                        .lat(event.getLocation().getLat())
                        .lon(event.getLocation().getLon())
                        .build())
                .paid(event.isPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.isRequestModeration())
                .state(event.getStateEvent())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    public static NewEventDto newEventDto(EventUpdateRequestDto eventUpdateRequestDto) {
        return NewEventDto.builder()
                .eventId(eventUpdateRequestDto.getEventId())
                .annotation(eventUpdateRequestDto.getAnnotation())
                .category(eventUpdateRequestDto.getCategory())
                .description(eventUpdateRequestDto.getDescription())
                .eventDate(eventUpdateRequestDto.getEventDate())
                .location(eventUpdateRequestDto.getLocation())
                .paid(eventUpdateRequestDto.getPaid())
                .participantLimit(eventUpdateRequestDto.getParticipantLimit())
                .requestModeration(eventUpdateRequestDto.getRequestModeration())
                .title(eventUpdateRequestDto.getTitle())
                .build();
    }
}

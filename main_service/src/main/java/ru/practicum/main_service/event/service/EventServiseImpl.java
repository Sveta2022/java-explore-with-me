package ru.practicum.main_service.event.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.client.EventClient;
import ru.practicum.main_service.category.model.CategoryEvent;
import ru.practicum.main_service.event.dao.EventStorage;
import ru.practicum.main_service.event.dto.*;
import ru.practicum.main_service.event.mapper.EventMapper;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.model.StateEvent;
import ru.practicum.main_service.exception.ConflictException;
import ru.practicum.main_service.exception.NotFoundObjectException;
import ru.practicum.main_service.requests.dao.RequestStorage;
import ru.practicum.main_service.requests.dto.EventRequestStatusUpdateResult;
import ru.practicum.main_service.requests.dto.ParticipationRequestDto;
import ru.practicum.main_service.requests.dto.ParticipationRequestStatusUpdate;
import ru.practicum.main_service.requests.mapper.RequestMapper;
import ru.practicum.main_service.requests.model.ParticipationRequest;
import ru.practicum.main_service.requests.model.RequestEventStatus;
import ru.practicum.main_service.user.dao.UserStorage;
import ru.practicum.main_service.user.model.Location;
import ru.practicum.main_service.user.model.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static ru.practicum.main_service.event.Formatter.FORMATTER;

@Service
@RequiredArgsConstructor
@Setter
@Transactional(readOnly = true)
public class EventServiseImpl implements EventService {

    private final EventStorage eventStorage;
    private final UserStorage userStorage;
    private final RequestStorage requestStorage;
    private final EventClient eventClient;


    @Override
    @Transactional
    public EventFullDto create(Long userId, NewEventDto eventNewDto) {
        User user = findUserbyId(userId);
        Event event = EventMapper.toEvent(eventNewDto);
        if (LocalDateTime.now().plusHours(2).isAfter(event.getEventDate())) {
            throw new ConflictException("дата и время на которые намечено событие не может быть раньше, " +
                    "чем через два часа от текущего момента");
        }
        event.setInitiator(user);

        return EventMapper.eventFullDto(eventStorage.save(event), 0);
    }

    @Override
    public List<EventShortDto> getAllEventsByUser(Long userId, int from, int size) {
        User user = userStorage.findById(userId).orElseThrow();
        Pageable pageable = PageRequest.of(
                from / size,
                size);
        return eventStorage.findAllByInitiatorId(userId, pageable)
                .stream()
                .map(event -> EventMapper.eventShortDto(event,
                        Objects.requireNonNull(getCountConfirmedRequests(event.getId())).orElse(0)))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EventFullDto update(NewEventDto newEventDto, Long eventId) {
        Event event = findEventbyId(eventId);  // событие в базе, которое меняем
        checkBeforeSaveNewEvent(newEventDto, event);
        return EventMapper.eventFullDto(eventStorage.save(event),
                getCountConfirmedRequests(eventId).orElse(0));
    }


    @Override
    public EventFullDto getEventByIdByCreator(Long userId, Long eventId) {
        EventFullDto eventFullDto = EventMapper.eventFullDto(eventStorage.findByIdAndInitiatorId(eventId, userId),
                getCountConfirmedRequests(eventId).orElse(0));
        return eventFullDto;
    }

    @Override
    @Transactional
    public List<EventShortDto> getEventsWithFilter(String text, List<Long> categoriesId, Boolean paid, String rangeStart, String rangeEnd, Boolean onlyAvailable, String sort, int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        String state = StateEvent.PUBLISHED.name();
        List<EventShortDto> events = eventStorage.findEvents(text, categoriesId, paid, state,
                        pageable)
                .stream()
                .filter(event -> rangeStart != null ?
                        event.getEventDate().isAfter(LocalDateTime.parse(rangeStart, FORMATTER)) :
                        event.getEventDate().isAfter(LocalDateTime.now())
                                && rangeEnd != null ? event.getEventDate().isBefore(LocalDateTime.parse(rangeEnd,
                                FORMATTER)) :
                                event.getEventDate().isBefore(LocalDateTime.MAX))
                .map(event -> EventMapper.eventShortDto(event, getCountConfirmedRequests(event.getId()).orElse(0)))//
                .collect(Collectors.toList());
        if (Boolean.TRUE.equals(onlyAvailable)) {
            events = events.stream().filter(shortEventDto ->
                    shortEventDto.getConfirmedRequests() < eventStorage
                            .findById(shortEventDto.getId()).get().getParticipantLimit() ||
                            eventStorage.findById(shortEventDto.getId()).get().getParticipantLimit() == 0
            ).collect(Collectors.toList());
        }
        if (sort != null) {
            switch (sort) {
                case "EVENT_DATE":
                    events = events
                            .stream()
                            .sorted(Comparator.comparing(EventShortDto::getEventDate))
                            .collect(Collectors.toList());
                    break;
                case "VIEWS":
                    events = events
                            .stream()
                            .sorted(Comparator.comparing(EventShortDto::getViews))
                            .collect(Collectors.toList());
                    break;
                default:
                    throw new ConflictException("Вариант сортировки: по дате события или по количеству просмотров");
            }
        }
        return events
                .stream()
                .peek(shortEventDto -> getViews(shortEventDto.getId()))
                .peek(shortEventDto -> shortEventDto.setViews(getViews(shortEventDto.getId())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EventFullDto getEventById(Long id) {

        Event event = findEventbyId(id);
        if (!event.getStateEvent().equals(StateEvent.PUBLISHED)) {
            throw new ConflictException("Cобытие должно быть опубликовано");
        }
        getViews(id);
        EventFullDto fullDto = EventMapper.eventFullDto(event, getCountConfirmedRequests(id).orElse(0));
        fullDto.setViews(getViews(id));
        return fullDto;

    }

    @Override
    @Transactional
    public EventFullDto updateByCreator(Long userId, Long eventId, EventUpdateRequestDto eventUpdateRequestDto) {

        Event event = findEventbyId(eventId);

        if (!event.getInitiator().getId().equals(userId)) {
            throw new ConflictException("Событие может изменить только текущий пользователь");
        }
        if (!event.getStateEvent().equals(StateEvent.PUBLISHED)) {

            NewEventDto newEventDto = EventMapper.newEventDto(eventUpdateRequestDto);
            checkBeforeSaveNewEvent(newEventDto, event);

            if (LocalDateTime.now().plusHours(2).isAfter(event.getEventDate())) {
                throw new ConflictException("дата и время на которые намечено событие не может быть раньше, " +
                        "чем через два часа от текущего момента");
            }
            switch (eventUpdateRequestDto.getStateAction()) {
                case SEND_TO_REVIEW:
                    event.setStateEvent(StateEvent.PENDING);
                    break;
                case CANCEL_REVIEW:
                    event.setStateEvent(StateEvent.CANCELED);
                    break;
            }
            EventFullDto eventFullDtoReturn = EventMapper.eventFullDto(eventStorage.save(event),
                    getCountConfirmedRequests(event.getId()).orElse(0));
            return eventFullDtoReturn;
        } else {
            throw new ConflictException("изменить можно только отмененные события или события " +
                    "в состоянии ожидания модерации");
        }
    }

    @Override
    public List<EventFullDto> getEventsByParametrs(List<Long> users, List<String> states, List<Long> categories, String rangeStart, String rangeEnd, Integer from, Integer size) {
        Pageable pageable = PageRequest.of(from / size, size);
        LocalDateTime start;
        if (rangeStart == null) {
            start = LocalDateTime.now();
        } else {
            start = LocalDateTime.parse(rangeStart, FORMATTER);
        }
        LocalDateTime end;
        if (rangeStart == null) {
            end = LocalDateTime.of(2099, 12, 30, 22, 22);
        } else {
            end = LocalDateTime.parse(rangeEnd, FORMATTER);
        }
        List<Event> events = eventStorage.findEventsByAdmin(users, states, categories, start, end, pageable);
        List<EventFullDto> eventFullDtos = events.stream().map(event ->
                EventMapper.eventFullDto(event, getCountConfirmedRequests(event.getId()).orElse(0))).collect(Collectors.toList());
        return eventFullDtos;
    }

    @Override
    public List<ParticipationRequestDto> getRequestEventByUser(Long userId, Long eventId) {
        Event event = findEventbyId(eventId);
        if (!event.getInitiator().getId().equals(userId)) {
            throw new ConflictException("Список запросов на участие в событие доступен только владельцу аккаунта");
        }
        User user = findUserbyId(userId);
        List<ParticipationRequestDto> participationRequest = requestStorage.findAllByEventId(event.getId()).stream().map(RequestMapper::toRequestEventDto).collect(Collectors.toList());
        return participationRequest;
    }

    @Override
    @Transactional
    public ParticipationRequestDto confirmRequestForEvent(Long userId, Long eventId, Long reqId) {
        Event event = findEventbyId(eventId);
        ParticipationRequest request = findRequestById(reqId);
        if (!event.getInitiator().getId().equals(userId)) {
            throw new ConflictException("Подтвердить запрос на участие в событие доступен только владельцу аккаунта");
        }
        if (!event.isRequestModeration() || event.getParticipantLimit() == 0) {
            throw new ConflictException("Запрос не может быть подтвержден");
        }
        request.setStatus(RequestEventStatus.CONFIRMED);
        return RequestMapper.toRequestEventDto(requestStorage.save(request));

    }

    @Override
    @Transactional
    public EventRequestStatusUpdateResult updateRequestStatusForEvent(Long userId, Long eventId, ParticipationRequestStatusUpdate participationRequestDto) {
        Event event = findEventbyId(eventId);
        List<Long> requestIds = participationRequestDto.getRequestIds();
        List<ParticipationRequest> requests = findAllRequestById(requestIds);
        if (event.getParticipantLimit() == 0 || !event.isRequestModeration()) {
            return null;
        }
        if (!event.getInitiator().getId().equals(userId)) {
            throw new ConflictException("Подтвердить запрос на участие в событие доступен только владельцу аккаунта");
        }
        if (event.getParticipantLimit()
                <= getCountConfirmedRequests(eventId).orElse(0)) {
            throw new ConflictException("достигнут лимит по заявкам на данное событие");
        }
        for (ParticipationRequest request : requests) {
            if (!request.getStatus().equals(RequestEventStatus.PENDING)) {
                throw new ConflictException("статус можно изменить только у заявок, находящихся в состоянии ожидания");
            }
        }
        List<ParticipationRequestDto> confirmRequest = new ArrayList<>();
        List<ParticipationRequestDto> rejectRequest = new ArrayList<>();
        switch (participationRequestDto.getStatus()) {
            case CONFIRMED:
                for (ParticipationRequest request : requests) {
                    if (event.getParticipantLimit()
                            <= getCountConfirmedRequests(eventId).orElse(0)) {
                        request.setStatus(RequestEventStatus.REJECTED);
                        requestStorage.save(request);
                        rejectRequest.add(RequestMapper.toRequestEventDto(request));
                    }
                    request.setStatus(RequestEventStatus.CONFIRMED);
                    requestStorage.save(request);
                    confirmRequest.add(RequestMapper.toRequestEventDto(request));
                }
                break;
            case REJECTED:
                for (ParticipationRequest request : requests) {
                    request.setStatus(RequestEventStatus.REJECTED);
                    requestStorage.save(request);
                    rejectRequest.add(RequestMapper.toRequestEventDto(request));
                }
        }
        return EventRequestStatusUpdateResult.builder()
                .confirmedRequests(confirmRequest)
                .rejectedRequests(rejectRequest)
                .build();
    }

    @Override
    @Transactional
    public EventFullDto updateEventByAdmin(Long eventId, EventUpdateRequestDto eventUpdateRequestDto) {
        Event event = findEventbyId(eventId);
        NewEventDto newEventDto = EventMapper.newEventDto(eventUpdateRequestDto);
        checkBeforeSaveNewEvent(newEventDto, event);
        LocalDateTime updateEventDate = event.getEventDate();
        if (updateEventDate.isBefore(LocalDateTime.now().minusHours(1))) {
            throw new ConflictException("Дата начала изменяемого события должна быть " +
                    "не ранее чем за час от даты публикации.");
        }
        if (!event.getStateEvent().equals(StateEvent.PENDING) && eventUpdateRequestDto.getStateAction()
                .equals(StateAction.PUBLISH_EVENT)) {
            throw new ConflictException("Cобытие можно публиковать, только если оно в состоянии ожидания публикации");
        }
        if (event.getStateEvent().equals(StateEvent.PENDING) && eventUpdateRequestDto.getStateAction()
                .equals(StateAction.PUBLISH_EVENT)) {
            event.setStateEvent(StateEvent.PUBLISHED);
            event.setPublishedOn(LocalDateTime.now());
        }
        if (!event.getStateEvent().equals(StateEvent.PUBLISHED) && eventUpdateRequestDto.getStateAction()
                .equals(StateAction.REJECT_EVENT)) {
            event.setStateEvent(StateEvent.CANCELED);
            event.setPublishedOn(null);
        }
        if (event.getStateEvent().equals(StateEvent.PUBLISHED) && eventUpdateRequestDto.getStateAction()
                .equals(StateAction.REJECT_EVENT)) {
            throw new ConflictException("Cобытие можно отклонить, только если оно еще не опубликовано ");
        }
        EventFullDto eventFullDto = EventMapper.eventFullDto(eventStorage.save(event), getCountConfirmedRequests(eventId).orElse(0));

        return eventFullDto;
    }

    private void checkBeforeSaveNewEvent(NewEventDto newEventDto, Event event) {
        if (newEventDto.getAnnotation() != null) {
            event.setAnnotation(newEventDto.getAnnotation());
        }
        if (newEventDto.getCategory() != null) {
            event.setCategory(CategoryEvent.builder().id(newEventDto.getCategory())
                    .build());
        }
        if (newEventDto.getDescription() != null) {
            event.setDescription(newEventDto.getDescription());
        }
        if (newEventDto.getEventDate() != null) {
            event.setEventDate(LocalDateTime.parse(newEventDto.getEventDate(), FORMATTER));
        }
        if (newEventDto.getLocation() != null) {
            Location location = Location.builder()
                    .lat(newEventDto.getLocation().getLat())
                    .lon(newEventDto.getLocation().getLon())
                    .build();
            event.setLocation(location);
        }
        if (newEventDto.getPaid() != null) {
            event.setPaid(newEventDto.getPaid());
        }
        if (newEventDto.getParticipantLimit() != null) {
            event.setParticipantLimit(newEventDto.getParticipantLimit());
        }
        if (newEventDto.getRequestModeration() != null) {
            event.setRequestModeration(newEventDto.getRequestModeration());
        }
        if (newEventDto.getTitle() != null) {
            event.setTitle(newEventDto.getTitle());
        }
    }

    private User findUserbyId(Long id) {
        return userStorage.findById(id)
                .orElseThrow(() -> new NotFoundObjectException("Пользователь с id " + id + " не найден"));
    }

    private Event findEventbyId(Long id) {
        return eventStorage.findById(id)
                .orElseThrow(() -> new NotFoundObjectException("Событие с id " + id + " не найдено"));
    }

    private ParticipationRequest findRequestById(Long reqId) {
        return requestStorage.findById(reqId).orElseThrow(() -> new NotFoundObjectException("Реквест с id " + reqId + " не найдена"));
    }

    private List<ParticipationRequest> findAllRequestById(List<Long> requestIds) {
        return requestStorage.findAllById(requestIds);
    }

    private Long getViews(long eventId) {
        ResponseEntity<Object> responseEntity = eventClient.getViews(
                LocalDateTime.of(2020, 9, 1, 0, 0),
                LocalDateTime.now(),
                List.of("/events/" + eventId),
                false);

        if (Objects.equals(responseEntity.getBody(), "")) {
            return ((Map<String, Long>) responseEntity.getBody()).get("hits");
        }
        return 0L;
    }

    private Optional<Integer> getCountConfirmedRequests(Long eventId) {
        return Optional.of(requestStorage.countParticipationRequestByEventIdAndStatus(eventId,
                RequestEventStatus.CONFIRMED));
    }
}

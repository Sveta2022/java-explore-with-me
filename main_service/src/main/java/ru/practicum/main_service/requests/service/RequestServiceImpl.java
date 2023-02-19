package ru.practicum.main_service.requests.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main_service.event.dao.EventStorage;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.event.model.StateEvent;
import ru.practicum.main_service.exception.NotFoundObjectException;
import ru.practicum.main_service.exception.ValidationException;
import ru.practicum.main_service.requests.dao.RequestStorage;
import ru.practicum.main_service.requests.dto.ParticipationRequestDto;
import ru.practicum.main_service.requests.mapper.RequestMapper;
import ru.practicum.main_service.requests.model.ParticipationRequest;
import ru.practicum.main_service.requests.model.RequestEventStatus;
import ru.practicum.main_service.user.dao.UserStorage;
import ru.practicum.main_service.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
@Transactional(readOnly = true)
public class RequestServiceImpl implements RequestService {
    private RequestStorage requestStorage;
    private EventStorage eventStorage;
    private UserStorage userStorage;

    @Autowired
    public RequestServiceImpl(RequestStorage requestStorage, EventStorage eventStorage, UserStorage userStorage) {
        this.requestStorage = requestStorage;
        this.eventStorage = eventStorage;
        this.userStorage = userStorage;
    }

    @Override
    @Transactional
    public ParticipationRequestDto create(Long userId, Long eventId) {
        if (requestStorage.findByEventIdAndRequesterId(eventId, userId) != null) {
            throw new ValidationException("Запрос от пользователя с id уже существует " + userId);
        }
        User user = getUserById(userId);
        Event event = eventStorage.findById(eventId)
                .orElseThrow(() -> new NotFoundObjectException("События с id " + eventId + " нет в списке"));
        if (event.getInitiator().getId().equals(userId)) {
            throw new ValidationException("Пользователь с id " + userId + "не может опубликовать запрос");
        }
        if (!event.getStateEvent().equals(StateEvent.PUBLISHED)) {
            throw new ValidationException("Событие не имеет статус Опубликовано");
        }
        ParticipationRequest request = ParticipationRequest.builder()
                .requester(user)
                .event(event)
                .created(LocalDateTime.now())
                .status(RequestEventStatus.PENDING)
                .build();
        if (!event.isRequestModeration() || event.getParticipantLimit() == 0) {
            request.setStatus(RequestEventStatus.CONFIRMED);
        }
        return RequestMapper.toRequestEventDto(requestStorage.save(request));
    }

    @Override
    public List<ParticipationRequestDto> getAllRequests(Long userId) {

        User user = getUserById(userId);
        List<ParticipationRequest> eventRequests = requestStorage.findAllByUser(user.getId());

        return eventRequests.stream()
                .map(RequestMapper::toRequestEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto cancelRequest(Long userId, Long requestId) {
        ParticipationRequest request = requestStorage.findById(requestId)
                .orElseThrow(() -> new NotFoundObjectException("Запроса с id " + requestId + " нет в списке"));
        User user = getUserById(userId);

        request.setStatus(RequestEventStatus.CANCELED);
        return RequestMapper.toRequestEventDto(requestStorage.save(request));
    }

    private User getUserById(Long userId) {
        return userStorage.findById(userId)
                .orElseThrow(() -> new NotFoundObjectException("Пользователя с id " + userId + " нет в списке"));

    }
}

package ru.practicum.main_service.event.model;


import java.util.Optional;


public enum StateEvent {
    PENDING,
    PUBLISHED,
    CANCELED;

    static Optional<StateEvent> from(String state) {
        for (StateEvent value : StateEvent.values()) {
            if (value.name().equals(state)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}

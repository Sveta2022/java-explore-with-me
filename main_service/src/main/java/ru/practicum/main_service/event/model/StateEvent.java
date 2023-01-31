package ru.practicum.main_service.event.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

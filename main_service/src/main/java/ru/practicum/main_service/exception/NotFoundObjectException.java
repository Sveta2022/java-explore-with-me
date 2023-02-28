package ru.practicum.main_service.exception;

public class NotFoundObjectException extends NullPointerException {
    public NotFoundObjectException(String error) {
        super(error);
    }
}
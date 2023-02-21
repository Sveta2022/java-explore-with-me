package ru.practicum.main_service.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String error) {
        super(error);
    }
}
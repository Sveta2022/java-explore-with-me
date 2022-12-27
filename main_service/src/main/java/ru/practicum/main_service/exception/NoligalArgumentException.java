package ru.practicum.main_service.exception;

public class NoligalArgumentException extends IllegalArgumentException {

    public NoligalArgumentException(String error) {
        super(error);
    }
}
package ru.practicum.main_service.exception;

public class ConflictException extends RuntimeException {


    public ConflictException(String error) {
        super(error);
    }
}

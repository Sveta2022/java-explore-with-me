package ru.practicum.main_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.main_service.user.controller.admin.AdminUserController;

import java.util.Map;

@RestControllerAdvice(assignableTypes = {AdminUserController.class})
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleValidation(final ValidationException v) {
        log.warn("400 {}", v.getMessage(), v);
        return new ResponseEntity<>(
                Map.of("error", v.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleNotFound(final NotFoundObjectException n) {
        log.warn("404 {}", n.getMessage(), n);
        return new ResponseEntity<>(
                Map.of("error", n.getMessage()),
                HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleAllIlligal(final IllegalArgumentException nL) {
        log.warn("500 {}", nL.getMessage(), nL);
        return new ResponseEntity<>(
                Map.of("error", nL.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleConflict(final ConflictException cL) {
        log.warn("409 {}", cL.getMessage(), cL);
        return new ResponseEntity<>(
                Map.of("error", cL.getMessage()),
                HttpStatus.CONFLICT);
    }
}
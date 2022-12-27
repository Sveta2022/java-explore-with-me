package ru.practicum.main_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.main_service.category.controller.AdminCategoryController;
import ru.practicum.main_service.category.controller.PublicCategoryController;
import ru.practicum.main_service.event.controller.AdminEventController;
import ru.practicum.main_service.event.controller.PrivateEventController;
import ru.practicum.main_service.event.controller.PublicEventController;
import ru.practicum.main_service.requests.controller.RequestController;
import ru.practicum.main_service.user.controller.AdminUserController;

import java.util.List;


@RestControllerAdvice(assignableTypes = {AdminUserController.class, AdminEventController.class,
        PrivateEventController.class, PublicEventController.class, AdminCategoryController.class,
        PublicCategoryController.class, RequestController.class})
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    public ResponseEntity<ApiError> handleValidation(final ValidationException v) {
        log.error("400 {}", v.getMessage(), v);
        ApiError apiError = new ApiError.ApiErrorBuilder()
                .errors(List.of(v.getClass().getName()))
                .message(v.getLocalizedMessage())
                .reason("For the requested operation the conditions are not met.")
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleNotFound(final NotFoundObjectException n) {
        log.error("404 {}", n.getMessage(), n);
        ApiError apiError = new ApiError.ApiErrorBuilder()
                .errors(List.of(n.getClass().getName()))
                .message(n.getLocalizedMessage())
                .reason("The required object was found.")
                .status(HttpStatus.NOT_FOUND)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);

    }


    @ExceptionHandler
    public ResponseEntity<ApiError> handleAllIlligal(final IllegalArgumentException nL) {
        log.error("500 {}", nL.getMessage(), nL);
        ApiError apiError = new ApiError.ApiErrorBuilder()
                .errors(List.of(nL.getClass().getName()))
                .message(nL.getLocalizedMessage())
                .reason("Error occurred")
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(apiError,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleConflict(final ConflictException cL) {
        log.error("409 {}", cL.getMessage(), cL);
        ApiError apiError = new ApiError.ApiErrorBuilder()
                .errors(List.of(cL.getClass().getName()))
                .message(cL.getLocalizedMessage())
                .reason("Integrity constraint has been violated")
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<>(apiError,
                HttpStatus.CONFLICT);
    }
}
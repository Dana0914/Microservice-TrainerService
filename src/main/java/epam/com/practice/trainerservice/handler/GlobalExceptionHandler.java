package epam.com.practice.trainerservice.handler;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "epam.com.practice.controller")
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException e) {
        logger.error("EntityNotFoundException occurred: ", e);

        ApiError response = new ApiError();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage("An unexpected error occurred: " + e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException e) {
        logger.error("MethodArgumentNotValidException occurred: ", e);


        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );


        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed for the request",
                LocalDateTime.now(),
                errors
        );


        return ResponseEntity.badRequest().body(apiError);

    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleNullPointerException(NullPointerException ex) {

        logger.error("NullPointerException occurred: ", ex);

        ApiError response = new ApiError();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("An unexpected error occurred. Please try again later: " + ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.internalServerError().body(response);


    }


}

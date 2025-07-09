package io.toughbox.auth.exception;

import io.toughbox.auth.exception.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("error", ex.getMessage());
        return ResponseEntity.ok().body(errorResponse);
    }

    @ExceptionHandler(InvalidAuthException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAuth(InvalidAuthException ex) {
        ErrorResponse errorResponse = new ErrorResponse("invalid.auth", ex.getMessage());
        return ResponseEntity.ok().body(errorResponse);
    }
}
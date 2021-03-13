package com.example.lw3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({OrderNotFoundException.class, ProductNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<CustomErrorResponse> handleNotFound(Exception exception, WebRequest webRequest) {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .error(exception.getMessage())
            .status(HttpStatus.NOT_FOUND.value())
            .build();

        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BlacklistCreationException.class, OrderCreationException.class, QuantityExceedsException.class})
    public ResponseEntity<CustomErrorResponse> handleBadRequest(Exception exception, WebRequest webRequest) {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .error(exception.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .build();

        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }
}

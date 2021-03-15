package com.example.lw3.exception;

public class BadPasswordException extends RuntimeException {
    public BadPasswordException(String message) {
        super(message);
    }
}

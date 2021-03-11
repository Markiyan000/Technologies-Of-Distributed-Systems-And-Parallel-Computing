package com.example.lw3.exception;

public class QuantityExceedsException extends RuntimeException {
    public QuantityExceedsException(String message) {
        super(message);
    }
}

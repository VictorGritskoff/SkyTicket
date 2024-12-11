package com.example.skyticketdemo.exceptions;

public class EmailNotAvailableException extends RuntimeException {
    public EmailNotAvailableException(String message) {
        super(message);
    }
}

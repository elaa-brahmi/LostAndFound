package com.example.registration;

public class EmailNotValidException extends Exception {
    public EmailNotValidException(String message) {
        super(message);
    }
}

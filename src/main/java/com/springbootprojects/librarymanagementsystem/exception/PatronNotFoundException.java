package com.springbootprojects.librarymanagementsystem.exception;

public class PatronNotFoundException extends RuntimeException {
    public PatronNotFoundException(String message) {
        super(message);

    }
}
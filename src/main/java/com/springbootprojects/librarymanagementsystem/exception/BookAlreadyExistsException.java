package com.springbootprojects.librarymanagementsystem.exception;

public class BookAlreadyExistsException  extends RuntimeException {
    public BookAlreadyExistsException(String message) {
        super(message);

    }
}

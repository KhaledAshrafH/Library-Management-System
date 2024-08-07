package com.springbootprojects.librarymanagementsystem.exception;

public class BookAlreadyBorrowedException extends RuntimeException {
    public BookAlreadyBorrowedException(String message) {
        super(message);

    }
}
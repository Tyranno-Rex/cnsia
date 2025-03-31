package com.cnsia.cloud_native_spring.exceptions;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super("Book with ISBN " + isbn + " does not exist");
    }
}

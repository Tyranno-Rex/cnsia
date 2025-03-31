package com.cnsia.cloud_native_spring.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
        super("Book with ISBN " + isbn + " already exists"); 
    }
    
}

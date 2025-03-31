package com.cnsia.cloud_native_spring.repositories;


import java.util.Optional;
import com.cnsia.cloud_native_spring.models.Book;


public interface BookInterface {
    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
}
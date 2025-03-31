package com.cnsia.cloud_native_spring.repositories;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

import com.cnsia.cloud_native_spring.models.Book;
import com.cnsia.cloud_native_spring.repositories.BookInterface;


@Repository
public class BookRepository implements BookInterface {
    private static final Map<String, Book> bookCatalog = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return bookCatalog.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ? Optional.of(bookCatalog.get(isbn)) : Optional.empty();
    }
    

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookCatalog.get(isbn) != null;
    }

    @Override
    public Book save(Book book) {
        bookCatalog.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        bookCatalog.remove(isbn);
    }
}

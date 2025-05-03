package com.cnsia.cloud_native_spring.services;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.cnsia.cloud_native_spring.repositories.BookRepository;
import com.cnsia.cloud_native_spring.models.Book;



@Component
@Profile("testData")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        var book1 = new Book("1234567890", "Spring in Action", "Craig Walls", 45.00);
        var book2 = new Book("0987654321", "Learning Java", "Cay S. Horstmann", 50.00);

        bookRepository.save(book1);
        bookRepository.save(book2);
    }
    
}

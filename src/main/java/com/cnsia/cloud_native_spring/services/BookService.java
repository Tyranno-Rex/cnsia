package com.cnsia.cloud_native_spring.services;
import org.springframework.stereotype.Service;
import com.cnsia.cloud_native_spring.models.Book;
import com.cnsia.cloud_native_spring.exceptions.BookAlreadyExistsException;
import com.cnsia.cloud_native_spring.exceptions.BookNotFoundException;
import com.cnsia.cloud_native_spring.repositories.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() 
            -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
            .map(existingBook -> {
                var bookToUpdate = new Book(
                    existingBook.isbn(),
                    book.title(),
                    book.author(),
                    book.price()
                    );
                return bookRepository.save(bookToUpdate);
            })
            .orElseGet(() -> addBookToCatalog(book));
    }
}

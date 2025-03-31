package com.cnsia.cloud_native_spring;

import com.cnsia.cloud_native_spring.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class CatalogServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void whenPostRequestThenBookCreated() {
        var expectedBook = new Book("1234567890", "Cloud Native Spring in Action", "Josh Long", 9.99);

        webTestClient
            .post()
            .uri("/books")
            .bodyValue(expectedBook)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(Book.class)
            .value(book -> {
                assertThat(book).isNotNull();
                // assertThat(book.isbn()).isEqualTo("1234567899");
                assertThat(book.isbn()).isEqualTo(expectedBook.isbn());
                assertThat(book.title()).isEqualTo(expectedBook.title());
                assertThat(book.author()).isEqualTo(expectedBook.author());
                assertThat(book.price()).isEqualTo(expectedBook.price());
            });
        }
    
}

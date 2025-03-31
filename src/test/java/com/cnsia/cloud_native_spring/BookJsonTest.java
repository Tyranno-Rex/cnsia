package com.cnsia.cloud_native_spring;

import com.cnsia.cloud_native_spring.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTest {
    
    @Autowired
    private JacksonTester<Book> json;

    @Test
    public void testSerialize() throws Exception {
        Book book = new Book("1234567890", "Cloud Native Java", "Josh Long", 9.99);
        var jsonContent = this.json.write(book);
        assertThat(jsonContent).extractingJsonPathStringValue("@.isbn").isEqualTo("1234567890");
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").isEqualTo("Cloud Native Java");
        assertThat(jsonContent).extractingJsonPathStringValue("@.author").isEqualTo("Josh Long");
        assertThat(jsonContent).extractingJsonPathNumberValue("@.price").isEqualTo(9.99);
    }

    @Test
    void testDeserialize() throws Exception {
        String content = "{\"isbn\":\"1234567890\",\"title\":\"Cloud Native Java\",\"author\":\"Josh Long\",\"price\":9.99}";
        var book = this.json.parse(content).getObject();

        assertThat(json.parse(content))
            .usingRecursiveComparison()
            .isEqualTo(new Book("1234567890", "Cloud Native Java", "Josh Long", 9.99));
    }

}

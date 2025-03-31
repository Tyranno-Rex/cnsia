package com.cnsia.cloud_native_spring.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record Book(
    @NotBlank(message = "The book ISBN must be defined")
    @Pattern(regexp = "^([0-9]{10}|[0-9]{13})$", message = "The book ISBN must be a 10 or 13 digit number")
    String isbn,

    @NotBlank(message = "The book title must be defined")
    String title,

    @NotBlank(message = "The book author must be defined")
    String author,

    @NotNull(message = "The book price must be defined")
    Double price
) {}
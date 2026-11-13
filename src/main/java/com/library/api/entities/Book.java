package com.library.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @NotBlank(message = "Book title cannot be blank")
    @Size(min = 3, max = 200, message = "Book title must be between 3 and 200 characters")
    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @NotBlank(message = "Author name cannot be blank")
    @Size(min = 3, max = 100, message = "Author name must be between 3 and 100 characters")
    @Column(name = "author", length = 100, nullable = false)
    private String author;

    @NotBlank(message = "ISBN cannot be blank")
    @Size(min = 10, max = 20, message = "ISBN must be between 10 and 20 characters")
    @Column(name = "isbn", length = 20, nullable = false, unique = true)
    private String isbn;

    @NotBlank(message = "Category cannot be blank")
    @Size(min = 3, max = 100, message = "Category must be between 3 and 100 characters")
    @Column(name = "category", length = 100, nullable = false)
    private String category;

    @NotNull(message = "Total copies cannot be null")
    @Min(value = 0, message = "Total copies cannot be negative")
    @Column(name = "total_copies", nullable = false)
    private Integer totalCopies;

    @NotNull(message = "Available copies cannot be null")
    @Min(value = 0, message = "Available copies cannot be negative")
    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies;

}
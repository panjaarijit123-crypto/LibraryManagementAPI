package com.library.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.library.api.entities.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {

    // JpaRepository provides basic CRUD operations for Book entity

    // Find books by category
    public List<Book> findByCategory(String category);

    // Find books by author name
    public List<Book> findByAuthor(String author);

    // Search books by title keyword
    public List<Book> findByTitleContaining(String keyword);

    // Find book by ISBN
    public List<Book> findByIsbn(String isbn);

}
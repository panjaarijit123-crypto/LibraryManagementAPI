package com.library.api.services;

import java.util.List;

import com.library.api.entities.Book;

public interface IBookService {

    // CRUD operations for Book entity

    public Book createBook(Book book);

    public Book updateBook(Book book);

    public boolean deleteBook(int bookId);

    public Book getBookById(int bookId);

    public List<Book> getAllBooks();

    // Find books by category
    public List<Book> getBooksByCategory(String category);

    // Find books by author
    public List<Book> getBooksByAuthor(String author);

    // Search books by title
    public List<Book> searchBooksByTitle(String keyword);

}
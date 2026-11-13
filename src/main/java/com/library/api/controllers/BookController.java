package com.library.api.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.api.entities.Book;
import com.library.api.services.IBookService;

@RestController
@RequestMapping("/library/api/book/")
public class BookController {

    @Autowired
    IBookService bookService;

    // Get all books
    @GetMapping(value = "/")
    public ResponseEntity<List<Book>> getAllBooks() {

        List<Book> books = bookService.getAllBooks();

        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    // Get book by ID
    @GetMapping(value = "/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable int bookId) {

        Book book = bookService.getBookById(bookId);

        if (book != null) {
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        }

        return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
    }

    // Get books by category
    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(
            @PathVariable String category) {

        List<Book> books = bookService.getBooksByCategory(category);

        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    // Create a new book
    @PostMapping(value = "/")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {

        Book savedBook = bookService.createBook(book);

        return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
    }

    // Update an existing book
    @PutMapping(value = "/")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) {

        Book updatedBook = bookService.updateBook(book);

        return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
    }

    // Delete book by ID
    @DeleteMapping(value = "/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) {

        boolean deleted = bookService.deleteBook(bookId);

        if (deleted) {
            return new ResponseEntity<String>(
                    "Book deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<String>(
                "Book not found", HttpStatus.NOT_FOUND);
    }
}
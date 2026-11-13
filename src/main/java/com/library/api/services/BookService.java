package com.library.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.api.entities.Book;
import com.library.api.exception.DuplicateResourceException;
import com.library.api.exception.ResourceNotFoundException;
import com.library.api.repositories.IBookRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService implements IBookService {

    @Autowired
    IBookRepository bookRepository;

    private void validateCopies(Book book) {
        if (book.getAvailableCopies() > book.getTotalCopies()) {
            throw new IllegalArgumentException(
                "Available copies cannot exceed total copies");
        }
    }

    @Override
    public Book createBook(Book book) {

        validateCopies(book);

        List<Book> existingBooks =
            bookRepository.findByIsbn(book.getIsbn());

        if (!existingBooks.isEmpty()) {
            throw new DuplicateResourceException(
                "A book with this ISBN already exists");
        }

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {

        Optional<Book> optional =
            bookRepository.findById(book.getBookId());

        if (!optional.isPresent()) {
            throw new ResourceNotFoundException("Book not found");
        }

        validateCopies(book);

        List<Book> existingBooks =
            bookRepository.findByIsbn(book.getIsbn());

        for (Book existingBook : existingBooks) {
            if (existingBook.getBookId() != book.getBookId()) {
                throw new DuplicateResourceException(
                    "Another book with this ISBN already exists");
            }
        }

        return bookRepository.save(book);
    }

    @Override
    public boolean deleteBook(int bookId) {

        Optional<Book> optional =
            bookRepository.findById(bookId);

        if (optional.isPresent()) {
            bookRepository.delete(optional.get());
            return true;
        }

        throw new ResourceNotFoundException("Book not found");
    }

    @Override
    public Book getBookById(int bookId) {

        Optional<Book> optional =
            bookRepository.findById(bookId);

        if (optional.isPresent()) {
            return optional.get();
        }

        throw new ResourceNotFoundException("Book not found");
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> searchBooksByTitle(String keyword) {
        return bookRepository.findByTitleContaining(keyword);
    }
}
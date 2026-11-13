package com.library.api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.api.entities.Book;
import com.library.api.entities.BookIssue;
import com.library.api.entities.Student;
import com.library.api.entities.IssueStatus;
import com.library.api.exception.ResourceNotFoundException;
import com.library.api.repositories.IBookIssueRepository;
import com.library.api.repositories.IBookRepository;
import com.library.api.repositories.IStudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookIssueService implements IBookIssueService {

    @Autowired
    IBookIssueRepository bookIssueRepository;

    @Autowired
    IBookRepository bookRepository;

    @Autowired
    IStudentRepository studentRepository;

    // Issue a book
    @Override
    public BookIssue issueBook(int studentId, int bookId) {

        Optional<Student> studentOptional =
                studentRepository.findById(studentId);

        if (!studentOptional.isPresent()) {
            throw new ResourceNotFoundException("Student not found");
        }

        Optional<Book> bookOptional =
                bookRepository.findById(bookId);

        if (!bookOptional.isPresent()) {
            throw new ResourceNotFoundException("Book not found");
        }

        Student student = studentOptional.get();
        Book book = bookOptional.get();

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalArgumentException("No copies available");
        }

        List<BookIssue> activeIssues =
                bookIssueRepository.findByStudentStudentIdAndStatus(
                        studentId, IssueStatus.ISSUED);

        if (activeIssues.size() >= 3) {
            throw new IllegalArgumentException(
                    "Student cannot issue more than 3 books");
        }

        BookIssue bookIssue = new BookIssue();

        bookIssue.setStudent(student);
        bookIssue.setBook(book);
        bookIssue.setIssueDate(LocalDate.now());
        bookIssue.setDueDate(LocalDate.now().plusDays(14));
        bookIssue.setStatus(IssueStatus.ISSUED);

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        bookRepository.save(book);

        return bookIssueRepository.save(bookIssue);
    }

    // Return a book
    @Override
    public BookIssue returnBook(int issueId) {

        Optional<BookIssue> issueOptional =
                bookIssueRepository.findById(issueId);

        if (!issueOptional.isPresent()) {
            throw new ResourceNotFoundException("Issue record not found");
        }

        BookIssue bookIssue = issueOptional.get();

        if (bookIssue.getStatus() == IssueStatus.RETURNED) {
            throw new IllegalArgumentException(
                    "Book has already been returned");
        }

        bookIssue.setReturnDate(LocalDate.now());
        bookIssue.setStatus(IssueStatus.RETURNED);

        Book book = bookIssue.getBook();

        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookRepository.save(book);

        return bookIssueRepository.save(bookIssue);
    }

    // Get issue by ID
    @Override
    public BookIssue getIssueById(int issueId) {

        Optional<BookIssue> optional =
                bookIssueRepository.findById(issueId);

        if (optional.isPresent()) {
            return optional.get();
        }

        throw new ResourceNotFoundException("Issue record not found");
    }

    // Get all issues
    @Override
    public List<BookIssue> getAllIssues() {
        return bookIssueRepository.findAll();
    }

    // Get issues by student
    @Override
    public List<BookIssue> getIssuesByStudent(int studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found");
        }

        return bookIssueRepository.findByStudentStudentId(studentId);
    }

    // Get overdue issues
    @Override
    public List<BookIssue> getOverdueIssues() {

        List<BookIssue> issuedBooks =
                bookIssueRepository.findByStatus(IssueStatus.ISSUED);

        return issuedBooks.stream()
                .filter(issue ->
                        issue.getDueDate().isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }
}
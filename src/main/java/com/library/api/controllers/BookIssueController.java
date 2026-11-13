package com.library.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.api.entities.BookIssue;
import com.library.api.services.IBookIssueService;

@RestController
@RequestMapping("/library/api/issue/")
public class BookIssueController {

    @Autowired
    IBookIssueService bookIssueService;

    // Get all issue records
    @GetMapping(value = "/")
    public ResponseEntity<List<BookIssue>> getAllIssues() {

        List<BookIssue> issues = bookIssueService.getAllIssues();

        return new ResponseEntity<List<BookIssue>>(issues, HttpStatus.OK);
    }

    // Get issue record by ID
    @GetMapping(value = "/{issueId}")
    public ResponseEntity<BookIssue> getIssueById(@PathVariable int issueId) {

        BookIssue issue = bookIssueService.getIssueById(issueId);

        if (issue != null) {
            return new ResponseEntity<BookIssue>(issue, HttpStatus.OK);
        }

        return new ResponseEntity<BookIssue>(HttpStatus.NOT_FOUND);
    }

    // Get issues by student ID
    @GetMapping(value = "/student/{studentId}")
    public ResponseEntity<List<BookIssue>> getIssuesByStudent(
            @PathVariable int studentId) {

        List<BookIssue> issues =
                bookIssueService.getIssuesByStudent(studentId);

        return new ResponseEntity<List<BookIssue>>(issues, HttpStatus.OK);
    }

    // Get overdue books
    @GetMapping(value = "/overdue")
    public ResponseEntity<List<BookIssue>> getOverdueIssues() {

        List<BookIssue> issues = bookIssueService.getOverdueIssues();

        return new ResponseEntity<List<BookIssue>>(issues, HttpStatus.OK);
    }

    // Issue a book to a student
    @PostMapping(value = "/{studentId}/{bookId}")
    public ResponseEntity<BookIssue> issueBook(
            @PathVariable int studentId,
            @PathVariable int bookId) {

        BookIssue issue = bookIssueService.issueBook(studentId, bookId);

        return new ResponseEntity<BookIssue>(issue, HttpStatus.CREATED);
    }

    // Return a book
    @PutMapping(value = "/return/{issueId}")
    public ResponseEntity<BookIssue> returnBook(@PathVariable int issueId) {

        BookIssue issue = bookIssueService.returnBook(issueId);

        return new ResponseEntity<BookIssue>(issue, HttpStatus.OK);
    }
}
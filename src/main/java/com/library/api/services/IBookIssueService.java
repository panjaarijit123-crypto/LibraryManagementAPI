package com.library.api.services;

import java.util.List;

import com.library.api.entities.BookIssue;

public interface IBookIssueService {

    public BookIssue issueBook(int studentId, int bookId);

    public BookIssue returnBook(int issueId);

    public BookIssue getIssueById(int issueId);

    public List<BookIssue> getAllIssues();

    public List<BookIssue> getIssuesByStudent(int studentId);

    public List<BookIssue> getOverdueIssues();

}
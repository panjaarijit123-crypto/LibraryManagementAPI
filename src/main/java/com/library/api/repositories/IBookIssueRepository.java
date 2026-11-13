package com.library.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.api.entities.BookIssue;
import com.library.api.entities.IssueStatus;

@Repository
public interface IBookIssueRepository
        extends JpaRepository<BookIssue, Integer> {

    List<BookIssue> findByStudentStudentId(int studentId);

    List<BookIssue> findByStatus(IssueStatus status);

    List<BookIssue> findByStudentStudentIdAndStatus(
            int studentId, IssueStatus status);

}
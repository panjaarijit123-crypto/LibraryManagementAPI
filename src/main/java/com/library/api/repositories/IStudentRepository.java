package com.library.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.api.entities.Student;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Integer> {

    // Find student by email
    public Student findByEmail(String email);

    // Find students by department
    public List<Student> findByDepartment(String department);

    // Search students by name
    public List<Student> findByStudentNameContaining(String keyword);

}
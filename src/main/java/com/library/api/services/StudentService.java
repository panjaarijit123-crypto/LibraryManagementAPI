package com.library.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.api.entities.Student;
import com.library.api.exception.DuplicateResourceException;
import com.library.api.exception.ResourceNotFoundException;
import com.library.api.repositories.IStudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService implements IStudentService {

    @Autowired
    IStudentRepository studentRepository;

    // Create a new student
    @Override
    public Student createStudent(Student student) {

        Student existingStudent =
                studentRepository.findByEmail(student.getEmail());

        if (existingStudent != null) {
            throw new DuplicateResourceException(
                    "A student with this email already exists");
        }

        return studentRepository.save(student);
    }

    // Update an existing student
    @Override
    public Student updateStudent(Student student) {

        Optional<Student> optional =
                studentRepository.findById(student.getStudentId());

        if (!optional.isPresent()) {
            throw new ResourceNotFoundException("Student not found");
        }

        Student existingStudent =
                studentRepository.findByEmail(student.getEmail());

        if (existingStudent != null &&
                existingStudent.getStudentId() != student.getStudentId()) {

            throw new DuplicateResourceException(
                    "Another student with this email already exists");
        }

        return studentRepository.save(student);
    }

    // Delete a student
    @Override
    public boolean deleteStudent(int studentId) {

        Optional<Student> optional =
                studentRepository.findById(studentId);

        if (optional.isPresent()) {

            Student student = optional.get();

            studentRepository.delete(student);

            return true;
        }

        throw new ResourceNotFoundException("Student not found");
    }

    // Get student by ID
    @Override
    public Student getStudentById(int studentId) {

        Optional<Student> optional =
                studentRepository.findById(studentId);

        if (optional.isPresent()) {
            return optional.get();
        }

        throw new ResourceNotFoundException("Student not found");
    }

    // Get all students
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by email
    @Override
    public Student getStudentByEmail(String email) {

        Student student = studentRepository.findByEmail(email);

        if (student == null) {
            throw new ResourceNotFoundException("Student not found");
        }

        return student;
    }

    // Get students by department
    @Override
    public List<Student> getStudentsByDepartment(String department) {
        return studentRepository.findByDepartment(department);
    }

    // Search students by name
    @Override
    public List<Student> searchStudentsByName(String keyword) {
        return studentRepository.findByStudentNameContaining(keyword);
    }
}
package com.library.api.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.api.entities.Student;
import com.library.api.services.IStudentService;

@RestController
@RequestMapping("/library/api/student/")
public class StudentController {

    @Autowired
    IStudentService studentService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Student>> getAllStudents() {

        List<Student> students = studentService.getAllStudents();

        return new ResponseEntity<List<Student>>(
                students, HttpStatus.OK);
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable int studentId) {

        Student student = studentService.getStudentById(studentId);

        return new ResponseEntity<Student>(
                student, HttpStatus.OK);
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<Student> getStudentByEmail(
            @PathVariable String email) {

        Student student = studentService.getStudentByEmail(email);

        return new ResponseEntity<Student>(
                student, HttpStatus.OK);
    }

    @GetMapping(value = "/department/{department}")
    public ResponseEntity<List<Student>> getStudentsByDepartment(
            @PathVariable String department) {

        List<Student> students =
                studentService.getStudentsByDepartment(department);

        return new ResponseEntity<List<Student>>(
                students, HttpStatus.OK);
    }

    @GetMapping(value = "/search/{keyword}")
    public ResponseEntity<List<Student>> searchStudentsByName(
            @PathVariable String keyword) {

        List<Student> students =
                studentService.searchStudentsByName(keyword);

        return new ResponseEntity<List<Student>>(
                students, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody Student student) {

        Student savedStudent = studentService.createStudent(student);

        return new ResponseEntity<Student>(
                savedStudent, HttpStatus.CREATED);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Student> updateStudent(
            @Valid @RequestBody Student student) {

        Student updatedStudent = studentService.updateStudent(student);

        return new ResponseEntity<Student>(
                updatedStudent, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{studentId}")
    public ResponseEntity<String> deleteStudent(
            @PathVariable int studentId) {

        studentService.deleteStudent(studentId);

        return new ResponseEntity<String>(
                "Student deleted successfully", HttpStatus.OK);
    }
}
package com.library.api.services;

import java.util.List;

import com.library.api.entities.Student;

public interface IStudentService {

    public Student createStudent(Student student);

    public Student updateStudent(Student student);

    public boolean deleteStudent(int studentId);

    public Student getStudentById(int studentId);

    public List<Student> getAllStudents();

    public Student getStudentByEmail(String email);

    public List<Student> getStudentsByDepartment(String department);

    public List<Student> searchStudentsByName(String keyword);

}